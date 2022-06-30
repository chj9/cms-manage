package com.chj9.cms.code.generator.utils;


import org.apache.commons.lang3.StringUtils;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil extends StringUtils {

    private static final Pattern linePattern = Pattern.compile("_(\\w)");

    public static String underlineToCamelCase(String str) {
        return underlineToCamelCase(str, false);
    }

    public static String underlineToCamelCase(String str, boolean firstUp) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase(Locale.ROOT));
        }
        matcher.appendTail(sb);
        decideFirstLetterCase(sb, firstUp);
        return sb.toString();
    }

    private static void decideFirstLetterCase(StringBuffer sb, boolean fristUp) {
        if (fristUp) {
            String fristWord = sb.substring(0, 1).toUpperCase(Locale.ROOT);
            sb.replace(0, 1, fristWord);
        } else {
            String fristWord = sb.substring(0, 1).toLowerCase(Locale.ROOT);
            sb.replace(0, 1, fristWord);
        }
    }

    /**
     * 驼峰转分隔字符串 eg: AbcDef -> abc-def
     *
     * @param str       驼峰字符串
     * @param delimiter 分隔符
     * @return
     */
    public static String unCamelWithDelimiter(String str, String delimiter) {
        if (StringUtils.isEmpty(str)) {
            return StringUtils.EMPTY;
        }
        if (StringUtils.isEmpty(delimiter)) {
            delimiter = "-";
        }
        String s = str.replaceAll("(?=[A-Z]+)", delimiter).trim().toLowerCase(Locale.ROOT);
        if (s.startsWith(delimiter)) {
            s = s.substring(1);
        }
        return s;
    }

    public static String unCamel(String str) {
        if (StringUtils.isEmpty(str)) {
            return StringUtils.EMPTY;
        }
        String delimiter = "-";
        return unCamelWithDelimiter(str, delimiter);
    }

    /**
     * dash字符串转小驼峰 eg: abc-def -> abcDef
     *
     * @param value dash字符串
     * @return 小驼峰字符串
     */
    public static String dashToCamelCase(String value, boolean firstUp) {
        StringBuffer sb = new StringBuffer(value.length());
        boolean upper = false;

        for (char c : value.toCharArray()) {
            if (c == '-') {
                upper = true;
                continue;
            }
            if (upper) {
                sb.append(Character.toUpperCase(c));
            } else {
                sb.append(c);
            }
            upper = false;
        }
        decideFirstLetterCase(sb, firstUp);
        return sb.toString();
    }

    public static String dashToCamelCase(String value) {
        return dashToCamelCase(value, false);
    }

}
