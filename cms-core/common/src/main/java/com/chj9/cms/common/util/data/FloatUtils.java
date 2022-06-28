package com.chj9.cms.common.util.data;

/**
*
*
* @author GuoJingtao
* @author <a href="mailto:gjt0206@163.com">guojingtao</a>
*/
public class FloatUtils {
	//private static final Logger logger = LoggerFactory.getLogger(FloatUtils.class);

	/**
	 * 将一个浮点数转换为整数，在转换前先*指定倍数(如果为null,那么默认为1） <br>
	 * 如果发生异常（例如字符串值无法转换为浮点数），那么返回默认值;<br>
	 * 转整的时候采用小数点后4舍5入的策略
	 * ------------------------------<br>
	 *
	 * @param valueString
	 * @param multiple
	 * @return
	 */
	public static Integer toIntFromFloatString(String valueString, Integer multiple, Integer defaultValue) {
		try {
			int multipleValue = (multiple == null ? 1 : multiple.intValue());
			float value = Float.parseFloat(valueString);
			return Math.round(value * multipleValue);
		} catch (Exception e) {
			//.debug("在将浮点数字符串{}使用倍数{}处理为整形数的时候发生异常：{}", valueString, multiple);
			return defaultValue;
		}
	}

	/**
	 * 根据参数字符串分析年龄范围，支持的参数格式包括:
	 * {数字(小)}(M/m)w - {数字(大)}(M/m) (从x - y, 如果带后缀m/M则表示为月份而不是年)
	 * {数字}{数字(小)}+ <br>
	 * ------------------------------<br>
	 *
	 * @param valueString
	 * @return
	 */
	public static int[] parseAgeRange(String valueString) {
		int minAge = 0;
		int maxAge = 9999;

		String[] parts = StringUtils.split(valueString, "-");
		if (parts == null || parts.length <= 0 || parts.length > 2) {
			//logger.debug("分析年龄范围{}失败。", valueString);
			return null;
		}
		if (parts.length == 1) {
			// 模式1， 该模式应该是 xx(mM)+
			String minAgeStr = StringUtils.trimToNull(parts[0]);
			if (minAgeStr == null) {
			//	logger.debug("分析年龄范围{}失败。", valueString);
				return null;
			}
			if (minAgeStr.endsWith("+") || minAgeStr.endsWith("＋")) {
				if (minAgeStr.endsWith("+")) {
					minAgeStr = StringUtils.substringBeforeLast(minAgeStr, "+");
				} else {
					minAgeStr = StringUtils.substringBeforeLast(minAgeStr, "＋");
				}
				Integer curAge = parseAgeMin(minAgeStr);
				if (curAge == null) {
				//	logger.debug("分析年龄范围{}失败。", valueString);
					return null;
				}
				minAge = curAge.intValue();
			} else {
				minAge = parseAgeMin(minAgeStr);
				maxAge = parseAgeMax(minAgeStr);
			}
			return new int[] { minAge, maxAge };
		} else if (parts.length == 2) {
			Integer curAgeMin = parseAgeMin(StringUtils.trimToNull(parts[0]));
			Integer curAgeMax = parseAgeMax(StringUtils.trimToNull(parts[1]));
			if (curAgeMin == null || curAgeMax == null || curAgeMin.intValue() > curAgeMax.intValue()) {
				//logger.debug("分析年龄范围{}失败。", valueString);
				return null;
			}
			return new int[] { curAgeMin.intValue(), curAgeMax.intValue() };
		} else {
			return null;
		}
	}

	/**
	 * 分析年岁，支持格式 x.y xM ,返回月数 <br>
	 * ------------------------------<br>
	 *
	 * @param minAgeStr
	 * @return
	 */
	public static Integer parseAgeMin(String age) {
		if (age == null) return null;
		String ageStr = StringUtils.lowerCase(age);
		if (ageStr.endsWith("m")) {
			return toIntFromFloatString(StringUtils.substringBeforeLast(ageStr, "m"), null, null);
		} else {
			return toIntFromFloatString(ageStr, 12, null);
		}
	}

	public static Integer parseAgeMax(String age) {
		if (age == null) return null;
		String ageStr = StringUtils.lowerCase(age);
		if (ageStr.endsWith("m")) {
			return toIntFromFloatString(StringUtils.substringBeforeLast(ageStr, "m"), null, null);
		} else {
			return toIntFromFloatString(ageStr, 12, null) + 11;
		}
	}
}
