package com.meetpraveen.app.handler;

import java.io.IOException;
import java.nio.channels.SocketChannel;

public class BlockingChannelHandler extends DecoratedHandler<SocketChannel> {
	
	public BlockingChannelHandler(Handler<SocketChannel> other) {
		super(other);
	}
	
	@Override
	public void handle(SocketChannel s) throws IOException {
		if (s.isConnected()) {
			super.handle(s);
		}
	}

}
