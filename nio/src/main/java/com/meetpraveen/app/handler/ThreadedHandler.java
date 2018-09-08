/**
 * 
 */
package com.meetpraveen.app.handler;

/**
 * @author praveesi
 *
 */
public class ThreadedHandler<T> extends UncheckedIOExceptionHandler<T> {

	public ThreadedHandler(Handler<T> other) {
		super(other);
	}

	public void handle(T s) {
		new Thread(() -> {
			super.handle(s);
		}).start();
	}
}
