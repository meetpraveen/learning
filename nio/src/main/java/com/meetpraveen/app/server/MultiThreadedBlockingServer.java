/**
 * 
 */
package com.meetpraveen.app.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.meetpraveen.app.handler.Handler;
import com.meetpraveen.app.handler.PrintingHandler;
import com.meetpraveen.app.handler.ThreadedHandler;
import com.meetpraveen.app.handler.TransformigrifyHandler;

/**
 * @author praveesi
 *
 */
public class MultiThreadedBlockingServer {
	public static void main(String[] args) throws IOException {
		ServerSocket sv = new ServerSocket(8080);
		Handler<Socket> handler = new ThreadedHandler<>(new PrintingHandler<>(new TransformigrifyHandler()));
		while (true) {
			Socket s = sv.accept();
			handler.handle(s);
		}
	}
}
