/**
 * 
 */
package com.meetpraveen.app.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;

import com.meetpraveen.app.handler.ExecutorServiceHandler;
import com.meetpraveen.app.handler.Handler;
import com.meetpraveen.app.handler.PrintingHandler;
import com.meetpraveen.app.handler.TransformigrifyHandler;

/**
 * @author praveesi
 *
 */
public class ExecutorServiceBlockingServer {
	public static void main(String[] args) throws IOException {
		ServerSocket sv = new ServerSocket(8080);
		Handler<Socket> handler = new ExecutorServiceHandler<>(
				new PrintingHandler<>(
						new TransformigrifyHandler()), 
				Executors.newFixedThreadPool(10));
		while (true) {
			Socket s = sv.accept();
			handler.handle(s);
		}
	}
}
