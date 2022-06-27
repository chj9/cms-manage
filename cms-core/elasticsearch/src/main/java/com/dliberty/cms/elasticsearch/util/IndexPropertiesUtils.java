package com.dliberty.cms.elasticsearch.util;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class IndexPropertiesUtils {

    private static final String SEPARATOR = ",";

    public static String buildSearchProperty(String... values) {
        if (values == null) {
            return null;
        }
        return Arrays.stream(values).filter(Objects::nonNull).collect(Collectors.joining(SEPARATOR));
    }

    public static String buildSearchProperty(List<String> values) {
        if (values == null) {
            return null;
        }
        return values.stream().filter(Objects::nonNull).collect(Collectors.joining(SEPARATOR));
    }

}
