/**
 * 
 */
package com.meetpraveen.app.handler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;

/**
 * @author praveesi
 *
 */
public class ExecutorServiceHandler<T> extends DecoratedHandler<T> {

	private ExecutorService pool;
	private Thread.UncaughtExceptionHandler exceptionHandler;

	public ExecutorServiceHandler(Handler<T> other, ExecutorService pool,
			Thread.UncaughtExceptionHandler exceptionHandler) {
		super(other);
		this.pool = pool;
		this.exceptionHandler = exceptionHandler;
	}
	
	public ExecutorServiceHandler(Handler<T> other, ExecutorService pool) {
		this(other, pool, (t, e) -> e.printStackTrace());
	}

	public void handle(T s) {
		pool.submit(new FutureTask<>(() -> {
			super.handle(s);
			return null;
		}) {

			@Override
			protected void setException(Throwable t) {
				exceptionHandler.uncaughtException(Thread.currentThread(), t);
			}
		});
	}
}
