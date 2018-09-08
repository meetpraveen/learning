package com.meetpraveen.app.handler;

import java.io.IOException;

public class PrintingHandler<T> extends DecoratedHandler<T> {

	public PrintingHandler(Handler<T> other) {
		super(other);
	}
	
	@Override
	public void handle(T s) throws IOException {
		System.out.println("Server connections on " + s);
		try{
			super.handle(s);
		} finally {
			System.out.println("Server disconnected " + s);
		}
	}
}
