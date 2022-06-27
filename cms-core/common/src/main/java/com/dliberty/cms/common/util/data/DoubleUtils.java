package com.dliberty.cms.common.util.data;

/**
 *
 *
 * @author GuoJingtao
 * @author <a href="mailto:gjt0206@163.com">guojingtao</a>
 */
public class DoubleUtils {
	//private static final Logger logger = LoggerFactory.getLogger(DoubleUtils.class);

	/**
	 * 将doubleValue转换成 double, 如果转换失败，或者 doubleValue为空，那么返回defaultValue <br>
	 * ------------------------------<br>
	 * 
	 * @param doubleValue
	 * @param defaultValue
	 * @return
	 */
	public static double defaultDouble(String doubleValue, double defaultValue) {
		String targetDoubleValue = StringUtils.trimToNull(doubleValue);
		if (targetDoubleValue == null) return defaultValue;

		try {
			return Double.parseDouble(doubleValue);
		} catch (Exception e) {
			//logger.debug("souce={}转换为double发生异常：{}", doubleValue, ExceptionInfoUtils.getDescription(e));
			return defaultValue;
		}
	}

	/**
	 * 将doubleValue转换成 double, 如果转换失败，或者 doubleValue为空，那么返回defaultValue <br>
	 * ------------------------------<br>
	 * 
	 * @param doubleValue
	 * @param defaultValue
	 * @return
	 */
	public static Double defaultDoubleObject(String doubleValue, Double defaultValue) {
		String targetDoubleValue = StringUtils.trimToNull(doubleValue);
		if (targetDoubleValue == null) return defaultValue;

		try {
			return Double.parseDouble(doubleValue);
		} catch (Exception e) {
			//logger.debug("souce={}转换为double发生异常：{}", doubleValue, ExceptionInfoUtils.getDescription(e));
			return defaultValue;
		}
	}

	/**
	 * 比较是否为0，我们取精度 0.00000001 <br>
	 * ------------------------------<br>
	 * 
	 * @param value
	 * @return
	 */
	private static final double MIN_VALUE = 0.00000001;
	public static boolean isZero(double value) {
		if (Math.abs(value) < MIN_VALUE) return true;
		return false;
	}
}
