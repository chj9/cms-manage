package com.chj9.cms.web.json;

import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public interface JsonWriter {
    /**
     * 将JavaBean JSON序列化成字节流。
     */
    byte[] write(Object object);

    void write(OutputStream outputStream, Object object);

    /**
     * 将JavaBean JSON序列化成字符串。
     */
    default String writeAsString(Object object) {
        return new String(write(object), StandardCharsets.UTF_8);
    }

    Map<String, Object> writeAsMap(Object object);

}