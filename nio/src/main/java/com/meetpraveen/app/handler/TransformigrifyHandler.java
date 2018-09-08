/**
 * 
 */
package com.meetpraveen.app.handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.meetpraveen.app.util.Utils;

/**
 * @author praveesi
 *
 */
public class TransformigrifyHandler implements Handler<Socket> {

	@Override
	public void handle(Socket s) throws IOException {
		try (s; InputStream inputStream = s.getInputStream(); OutputStream outputStream = s.getOutputStream()) {
			int data;
			// inputStream.transferTo(outputStream);
			while ((data = inputStream.read()) != -1) {
				outputStream.write(Utils.transmogrifyData(data));
			}
		}
	}

}
