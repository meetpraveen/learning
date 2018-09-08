package com.meetpraveen.app.handler;

import java.io.IOException;
import java.io.UncheckedIOException;

public class UncheckedIOExceptionHandler<T> extends DecoratedHandler<T> {

	public UncheckedIOExceptionHandler(Handler<T> other) {
		super(other);
	}
	
	@Override
	public void handle(T s) {
		try {
			super.handle(s);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

}
