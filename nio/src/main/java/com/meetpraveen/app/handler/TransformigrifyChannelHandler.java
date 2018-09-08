/**
 * 
 */
package com.meetpraveen.app.handler;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import com.meetpraveen.app.util.Utils;

/**
 * @author praveesi
 *
 */
public class TransformigrifyChannelHandler implements Handler<SocketChannel> {

	@Override
	public void handle(SocketChannel sc) throws IOException {
		ByteBuffer buff = ByteBuffer.allocateDirect(80);
		int read = sc.read(buff);
		if (read == -1) {
			sc.close();
			return;
		}
		if (read > 0) {
			Utils.transmogrifyData(buff);
			while (buff.hasRemaining()) {
				sc.write(buff);
			}
			buff.compact();
		}
	}

}
