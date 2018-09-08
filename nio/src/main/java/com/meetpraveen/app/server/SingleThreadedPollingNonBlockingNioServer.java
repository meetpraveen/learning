/**
 * 
 */
package com.meetpraveen.app.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Collection;

import com.meetpraveen.app.handler.Handler;
import com.meetpraveen.app.handler.TransformigrifyChannelHandler;

/**
 * @author praveesi
 *
 */
public class SingleThreadedPollingNonBlockingNioServer {
	public static void main(String[] args) throws IOException {
		ServerSocketChannel sv = ServerSocketChannel.open();
		sv.bind(new InetSocketAddress(8080));
		sv.configureBlocking(false);
		Handler<SocketChannel> handler = new TransformigrifyChannelHandler();
		Collection<SocketChannel> sockets = new ArrayList<>();
		while (true) {
			SocketChannel sc = sv.accept(); //almost always null
			if (sc != null) {
				sockets.add(sc);
				System.out.println("Connected to " + sc);
				sc.configureBlocking(false);
			}
			for (SocketChannel socketChannel : sockets) {
				if (socketChannel.isConnected()) {
					handler.handle(socketChannel);
				}
			}
			sockets.removeIf(socket -> !socket.isConnected());
		}
	}
}
