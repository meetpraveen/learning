/**
 * 
 */
package com.meetpraveen.app.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.Executors;

import com.meetpraveen.app.handler.BlockingChannelHandler;
import com.meetpraveen.app.handler.ExecutorServiceHandler;
import com.meetpraveen.app.handler.Handler;
import com.meetpraveen.app.handler.PrintingHandler;
import com.meetpraveen.app.handler.TransformigrifyChannelHandler;

/**
 * @author praveesi
 *
 */
public class BlockingNioServer {
	public static void main(String[] args) throws IOException {
		ServerSocketChannel sv = ServerSocketChannel.open();
		sv.bind(new InetSocketAddress(8080));
		Handler<SocketChannel> handler = new ExecutorServiceHandler<>(
				new PrintingHandler<>(
						new BlockingChannelHandler(
								new TransformigrifyChannelHandler())),
				Executors.newFixedThreadPool(10));
		while (true) {
			SocketChannel s = sv.accept();
			handler.handle(s);
		}
	}
}
