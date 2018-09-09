package com.meetpraveen.app.handler;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;

public class AcceptHandler implements Handler<SelectionKey> {
	private Map<SocketChannel, Queue<ByteBuffer>> pending;

	public AcceptHandler(Map<SocketChannel, Queue<ByteBuffer>> pending) {
		this.pending = pending;
	}

	@Override
	public void handle(SelectionKey s) throws IOException {
		ServerSocketChannel ssc = (ServerSocketChannel) s.channel();
		SocketChannel sc = ssc.accept();
		System.out.println("Connected to " + sc);
		sc.configureBlocking(false);
		pending.put(sc, new ArrayDeque<>());
		sc.register(s.selector(), SelectionKey.OP_READ);
	}
}
