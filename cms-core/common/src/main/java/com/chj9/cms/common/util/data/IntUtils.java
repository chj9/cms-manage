package com.chj9.cms.common.util.data;


import java.util.LinkedList;
import java.util.List;


/**
*
*
* @author GuoJingtao
* @author <a href="mailto:gjt0206@163.com">guojingtao</a>
*/
public class IntUtils {
	//private static final Logger logger = LoggerFactory.getLogger(IntUtils.class);

	public static String[] bitCombine2String(Integer value, String[] initialValues){
		if (value == null) return null;

		List<String> lstResults = new LinkedList<String>();
		if (initialValues != null){
			for (String initialValue : initialValues) {
				if (initialValue != null && initialValue.length() > 0){
					lstResults.add(initialValue);
				}
			}
		}
		for (int i=0; i < 32; i++){
			if ( (value & (1 << i)) != 0 ){
				lstResults.add(String.valueOf(1<<i));
			}
		}

		return lstResults.toArray(new String[lstResults.size()]);
	}

	/**
	 * 按照给定字符串数组指定的byte位构建一个int值
	 * <br/>------------------------------<br/>
	 * @param bits
	 * @return
	 */
	public static int bitCombineToInt(String[] bits,int initialValue){
		if (bits == null || bits.length == 0 ) return 0;

		int result = initialValue;
		for (String bit : bits) {
			int value = defaultInt(bit, 0);
			switch (value) {
			case 1:
				result |= 1;
				break;
			case 2:
				result |= 2;
				break;
			case 4:
				result |= 4;
				break;
			case 8:
				result |= 8;
				break;
			case 16:
				result |= 16;
				break;
			case 32:
				result |= 32;
				break;
			case 64:
				result |= 64;
				break;
			case 128:
				result |= 128;
				break;
			case 256:
				result |= 256;
				break;
			case 512:
				result |= 512;
				break;
			case 1024:
				result |= 1024;
				break;
			case 2048:
				result |= 2048;
				break;
			case 4096:
				result |= 4096;
				break;
			case 8192:
				result |= 8192;
				break;
			case 16384:
				result |= 16384;
				break;
			case 32768:
				result |= 32768;
				break;
			case 65536:
				result |= 65536;
				break;
			default:
				break;
			}
		}

		return result;
	}

	public static int defaultInt(byte input, int defaultValue){
		return input;
	}

	public static int defaultInt(Byte input, int defaultValue){
		if (input != null){
			return input.intValue();
		}else{
			return defaultValue;
		}
	}


	/**
	 * 返回输入的参数的 int value, 如果输入的input为空(null)，那么返回 defaultValue
	 *
	 * @param input
	 * @param defaultValue
	 * @return
	 */
	public static int defaultInt(Integer input, int defaultValue){
		if (input == null){
			return defaultValue;
		}

		return input.intValue();
	}

	public static int defaultInt(String value, int defaultValue){
		if (StringUtils.isEmpty(value)) return defaultValue;
		Integer intvalue = null;
		try {
			intvalue = Integer.parseInt(value);
		} catch (Exception e) {
		//	logger.info("将字符串转换为整形数字失败：value=" + value + "：" + e.getMessage());
			return defaultValue;
		}

		return intvalue.intValue() ;
	}

	/**
	 * 分析value字符串数据，针对每一个value转换为int类型值，如果①转换失败，或者　②　string的值为null或者空，那么设置为defaultValue <br>
	 * 如果value数组就是null, 那么我们将直接返回null
	 * ------------------------------<br>
	 *
	 * @param value
	 * @param defaultValue
	 * @return
	 */
	public static int[] defaultIntArray(String[] value, int defaultValue) {
		if (value == null) return null;
		int[] result = new int[value.length];
		int loop = 0;
		for (String curValue : value) {
			result[loop++] = defaultInt(curValue, defaultValue);
		}

		return result;
	}

	public static Integer defaultIntegerObject(String input, Integer defaultVale){
		if (input == null || input.length() <= 0) return defaultVale;

		try {
			return Integer.parseInt(input);
		} catch (NumberFormatException nfe) {
			return defaultVale;
		}
	}

	public static Integer defaultIntegerObject(Object input, Integer defaultVale) {
		if (input == null) return defaultVale;

		try {
			return Integer.parseInt(input.toString());
		} catch (NumberFormatException nfe) {
			return defaultVale;
		}
	}

	/** 返回一个integer的.tostring() 如果该 integer == null, 那么返回defaultString */
	public static String defaultString(Integer integer, String defaultString) {
		if (integer == null) return defaultString;
		return integer.toString();
	}

	/**
	 * 返回输入的参数的 int value,
	 * <li>如果输入的参数input为负数，那么返回defaultValue
	 * @param input
	 * @param defaultValue
	 * @return
	 */
	public static int defaultUnsignedInt(int input, int defaultValue){
		if (input < 0){
			return defaultValue;
		}
		return input;
	}

	/**
	 * 返回输入的参数的 int value,
	 * <li>如果输入的input为空(null)，那么返回 defaultValue
	 * <li>如果输入的参数input为负数，那么返回defaultValue
	 * @param input
	 * @param defaultValue
	 * @return
	 */
	public static int defaultUnsignedInt(Integer input, int defaultValue){
		if (input == null || input.intValue() < 0){
			return defaultValue;
		}

		return input.intValue();
	}

	/**
	 * 比较两个 Interger是否相等
	 * <br/>------------------------------<br/>
	 * @param id
	 * @param id2
	 * @return
	 */
	public static boolean notEquals(Integer int1, Integer int2) {
		if (int1 == null && int2 == null) return false;		// 两个都是null, 那么返回true
		if (int1 == null || int2 == null) return true;		// 只有一个为null, 那么返回false
		return ( !(int1.intValue() == int2.intValue()) );
	}

	/**
	 * 检查两个Integer是否相等，只要任何一个（或两个都是）null，那么就返回false
	 * 否则返回两个integer的intValue()是否相等
	 *
	 * @param integer1
	 * @param integer2
	 * @return
	 */
	public static boolean valueEquals(Integer integer1, Integer integer2){
		if (integer1 == null || integer2 == null){
			return false;
		}

		return (integer1.intValue() == integer2.intValue());
	}

	/**
	 * 比较一个Integer是否与一个int值相同，如果Integer为null,那么返回false
	 * <br>
	 * ------------------------------<br>
	 *
	 * @param srcInteger
	 * @param intValue
	 * @return
	 */
	public static boolean equalInt(Integer srcInteger, int intValue) {
		return (srcInteger == null ? false : (srcInteger.intValue() == intValue));
	}

	/**
	 * <br>
	 * ------------------------------<br>
	 *
	 * @param filterConditions
	 * @return
	 */
	public static Integer[] buildIntegerArray(String[] stringArrays) {
		if (stringArrays == null || stringArrays.length == 0) return null;

		Integer[] results = new Integer[stringArrays.length];
		int nCount = 0;
		for (String stringValue : stringArrays) {
			results[nCount++] = IntUtils.defaultIntegerObject(stringValue, null);
		}

		return results;
	}
}

