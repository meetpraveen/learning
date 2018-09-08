/**
 * 
 */
package com.meetpraveen.app;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author praveesi
 *
 */
public class NastChamp {

	/**
	 * @param args
	 * @throws IOException
	 * @throws UnknownHostException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		Socket[] sockets = new Socket[30000];
		for (int i = 0; i < sockets.length; i++) {
			sockets[i] = new Socket("localhost", 8080);
		}
		Thread.sleep(300_000);
	}

}
