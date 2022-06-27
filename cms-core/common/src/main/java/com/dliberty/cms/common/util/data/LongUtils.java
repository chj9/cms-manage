package com.dliberty.cms.common.util.data;




/**
*
*
* @author GuoJingtao
* @author <a href="mailto:gjt0206@163.com">guojingtao</a>
*/
public class LongUtils {
	public static long defaultLong(Object input, long defaultValue) {
		if (input == null) return defaultValue;
		return defaultLong(input.toString(), defaultValue);
	}

	public static long defaultLong(String input, long defaultVale) {
		if (input == null || input.length() <= 0) return defaultVale;

		try {
			return Long.parseLong(input);
		} catch (NumberFormatException nfe) {
			return defaultVale;
		}
	}

	public static Long defaultLongObject(String input, Long defaultVale) {
		if (input == null || input.length() <= 0) return defaultVale;

		try {
			return Long.parseLong(input);
		} catch (NumberFormatException nfe) {
			return defaultVale;
		}
	}

}
