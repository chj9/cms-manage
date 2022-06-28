package com.chj9.cms.common.util.data;


/**
 *
 *
 * @author GuoJingtao
 * @author <a href="mailto:gjt0206@163.com">guojingtao</a>
 */
public class BooleanUtils {
	/**
	 * ● 分析字符串value,如果为null，那么返回 defaultValue的值。 <br>
	 * ● 如果不为空,那么与 true/false, yes/no, on/off 比较，返回 true/false, 如果比较失败，那么返回 defaultValue<br>
	 * ------------------------------<br>
	 *
	 * @param value
	 * @param defaultValue
	 * @return
	 */
	public static boolean defaultBoolean(String value, boolean defaultValue) {
		Boolean result = defaultBooleanObject(value, null);
		if (result == null) {
			return defaultValue;
		} else {
			return result.booleanValue();
		}
	}

	public static Boolean defaultBooleanObject(String value, Boolean defaultValue) {
		if (value == null) return defaultValue;
		String trimedLowercaseValue = value.trim().toLowerCase();
		if (trimedLowercaseValue.equals("true") || trimedLowercaseValue.equals("yes") || trimedLowercaseValue.equals("on") || trimedLowercaseValue.equals("y")) {
			return Boolean.TRUE;
		} else if (trimedLowercaseValue.equals("false") || trimedLowercaseValue.equals("no") || trimedLowercaseValue.equals("off") || trimedLowercaseValue.equals("n")) {
			return Boolean.FALSE;
		} else {
			return defaultValue;
		}
	}

}
