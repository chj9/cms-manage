package com.dliberty.cms.common.util.data;

/**
*
*
* @author GuoJingtao
* @author <a href="mailto:gjt0206@163.com">guojingtao</a>
*/
public class ByteUtils {

	public static String[] bitCombine2String(Byte value, String[] initialValues){
		if (value == null) return null;
		return IntUtils.bitCombine2String(Integer.valueOf(value.intValue()),initialValues);
	}

	/**
	 * 将一个int转换为byte
	 * <br>------------------------------<br>
	 * @param i
	 * @return
	 */
	public static byte byteValue(int i) {
		return (byte) i;
	}

	/**
	 * 返回input的byte value， 如果 input==null, 那么返回defaultValue
	 * <br/>------------------------------<br/>
	 * @param input
	 * @param defaultValue
	 * @return
	 */
	public static byte defaultByte(Byte input, byte defaultValue){
		if (input != null){
			return input.byteValue();
		}else{
			return defaultValue;
		}
	}

	/**
	 * 返回input的byte value， 如果 input==null或者无法转换为byte, 那么返回defaultValue <br/>
	 * ------------------------------<br/>
	 *
	 * @param input
	 * @param defaultValue
	 * @return
	 */
	public static byte defaultByte(String value, byte defaultValue) {
		if (StringUtils.isEmpty(value)) return defaultValue;

		byte byteValue = defaultValue;
		try {
			byteValue = Byte.parseByte(value);
		} catch (Exception e) {
			//logger.info("将字符串转换为整形数字失败：value=" + value + "：" + e.getMessage());
			return defaultValue;
		}

		return byteValue;
	}

	public static byte valueAnd(Byte oneByte, byte defaultValue,byte andByteValue){
		byte value = ((oneByte==null)?defaultValue:oneByte.byteValue());
		return (byte)(value & andByteValue);
	}

	/**
	 * 检查两个Byte是否相等，只要任何一个（或两个都是）null，那么就返回false
	 * 否则返回两个Byte的byteValue()是否相等
	 *
	 * @param byte1
	 * @param byte2
	 * @return
	 */
	public static boolean valueEquals(Byte byte1 , Byte byte2){
		if (byte1 == null || byte2 == null){
			return false;
		}
		return (byte1.byteValue() == byte2.byteValue());
	}

	public static byte valueOr(Byte oneByte, byte defaultValue, byte orByteValue){
		byte value = ((oneByte==null)?defaultValue:oneByte.byteValue());
		return (byte)(value | orByteValue);
	}

	/**
	 * 分析value字符串数据，针对每一个value转换为int类型值，如果①转换失败，或者　②　string的值为null或者空，那么设置为defaultValue <br>
	 * 如果value数组就是null, 那么我们将直接返回null <br>
	 * ------------------------------<br>
	 *
	 * @param filterOrderStatus
	 * @param i
	 * @return
	 */
	public static byte[] defaultByteArray(String[] value, byte defaultValue) {
		if (value == null) return null;
		byte[] result = new byte[value.length];
		int loop = 0;
		for (String curValue : value) {
			result[loop] = defaultByte(curValue, defaultValue);
		}

		return result;
	}

}
