/**
 * 
 */
package com.meetpraveen.app.handler;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Map;
import java.util.Queue;

/**
 * @author praveesi
 *
 */
public class WriteSelectorHandler implements Handler<SelectionKey> {
	private Map<SocketChannel, Queue<ByteBuffer>> pending;
	
	public WriteSelectorHandler(Map<SocketChannel, Queue<ByteBuffer>> pending) {
		this.pending = pending;
	}

	/* (non-Javadoc)
	 * @see com.meetpraveen.app.handler.Handler#handle(java.lang.Object)
	 */
	@Override
	public void handle(SelectionKey s) throws IOException {
		SocketChannel sc = (SocketChannel) s.channel();
		Queue<ByteBuffer> queue = pending.get(sc);
		while (!queue.isEmpty()) {
			ByteBuffer buff = queue.peek();
			int write = sc.write(buff);
			if (write == -1) {
				sc.close();
				System.out.println("Closing the connection " + sc);
				pending.remove(sc);
			}
			if (buff.hasRemaining()) return;
			queue.remove();
		}
		s.interestOps(SelectionKey.OP_READ);
	}
}
