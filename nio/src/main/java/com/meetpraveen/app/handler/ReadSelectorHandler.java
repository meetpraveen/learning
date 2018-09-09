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

import com.meetpraveen.app.util.Utils;

/**
 * @author praveesi
 *
 */
public class ReadSelectorHandler implements Handler<SelectionKey> {
	private Map<SocketChannel, Queue<ByteBuffer>> pending;
	
	public ReadSelectorHandler(Map<SocketChannel, Queue<ByteBuffer>> pending) {
		this.pending = pending;
	}

	/* (non-Javadoc)
	 * @see com.meetpraveen.app.handler.Handler#handle(java.lang.Object)
	 */
	@Override
	public void handle(SelectionKey s) throws IOException {
		SocketChannel sc = (SocketChannel) s.channel();
		ByteBuffer buff = ByteBuffer.allocateDirect(80);
		int read = sc.read(buff);
		if (read == -1) {
			sc.close();
			pending.remove(sc);
			System.out.println("Disconnection of " +  sc);
			return;
		}
		Utils.transmogrifyData(buff);
		pending.get(sc).add(buff);
		s.interestOps(SelectionKey.OP_WRITE);
	}

}
