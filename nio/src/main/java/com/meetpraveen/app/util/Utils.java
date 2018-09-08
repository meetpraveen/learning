package com.meetpraveen.app.util;

public class Utils {
	public static int transmogrifyData(int data) {
		return Character.isLetter(data) ? data ^ ' ' : data;
	}
}
