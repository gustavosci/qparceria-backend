package br.com.qparceria.resources.utils;

import java.math.BigDecimal;

public class URL {

	public static BigDecimal toBigDecimal(String str) {
		return new BigDecimal(str);
	}

	public static boolean toBoolean(String str) {
		if(str.equalsIgnoreCase("false")) {
			return false;
		} else {
			return true;
		}
	}

}
