package com.meetpraveen.app.util;

import java.nio.ByteBuffer;

public class Utils {
	public static int transmogrifyData(int data) {
		return Character.isLetter(data) ? data ^ ' ' : data;
	}

	public static void transmogrifyData(ByteBuffer buff) {
		buff.flip();
		for (int i = 0; i < buff.limit(); i++) {
			buff.put(i, (byte) transmogrifyData(buff.get(i)));
		}
	}
}
