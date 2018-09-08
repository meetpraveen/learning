/**
 * 
 */
package com.meetpraveen.app.handler;

import java.io.IOException;

/**
 * @author praveesi
 *
 */
public interface Handler<T> {
	
	void handle(T s) throws IOException;

}
