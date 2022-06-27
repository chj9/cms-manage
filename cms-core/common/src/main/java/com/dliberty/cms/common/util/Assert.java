package com.dliberty.cms.common.util;

import java.util.Collection;

public abstract class Assert {
    public static void notEmpty(Object object, String message) {
        if (null == object
                || (object instanceof String && ((String) object).isEmpty())
                || (object instanceof Collection && ((Collection<?>) object).isEmpty())) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notEmpty(Object object) {
        notEmpty(object, "非空断言失败");
    }
}
