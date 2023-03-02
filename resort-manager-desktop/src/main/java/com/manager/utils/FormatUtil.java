package com.manager.utils;

import java.text.DecimalFormat;

public class FormatUtil {
	private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,##0");

	public static String formatNumber(double data) {
		return DECIMAL_FORMAT.format(data);
	}

	public static int parseNumber(String str) {
		try {
			return DECIMAL_FORMAT.parse(str).intValue();
		} catch (Exception e) {
			throw new NumberFormatException(String.format("Invalid number format %s", str));
		}
	}
}
