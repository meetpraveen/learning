/**
 * 
 */
package com.meetpraveen.app.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.meetpraveen.app.handler.AcceptHandler;
import com.meetpraveen.app.handler.Handler;
import com.meetpraveen.app.handler.ReadSelectorHandler;
import com.meetpraveen.app.handler.WriteSelectorHandler;

/**
 * @author praveesi
 *
 */
public class SingleThreadSelectorBasedNonBlockingNioServer {
	public static void main(String[] args) throws IOException {
		ServerSocketChannel sv = ServerSocketChannel.open();
		sv.bind(new InetSocketAddress(8080));
		sv.configureBlocking(false);
		Selector selector = Selector.open();
		sv.register(selector, SelectionKey.OP_ACCEPT);

		Map<SocketChannel, Queue<ByteBuffer>> pending = new ConcurrentHashMap<>();
		Handler<SelectionKey> acceptHandler = new AcceptHandler(pending);
		Handler<SelectionKey> readHandler = new ReadSelectorHandler(pending);
		Handler<SelectionKey> writeHandler = new WriteSelectorHandler(pending);

		while (true) {
			selector.select();
			Set<SelectionKey> selectedKeys = selector.selectedKeys();
			for (Iterator<SelectionKey> iterator = selectedKeys.iterator(); iterator.hasNext();) {
				SelectionKey selectionKey = iterator.next();
				iterator.remove();
				if (selectionKey.isValid()) {
					if (selectionKey.isAcceptable()) {
						acceptHandler.handle(selectionKey);
					} else if (selectionKey.isReadable()) {
						readHandler.handle(selectionKey);
					} else if (selectionKey.isWritable()) {
						writeHandler.handle(selectionKey);
					}
				}
			}
		}
	}
}
