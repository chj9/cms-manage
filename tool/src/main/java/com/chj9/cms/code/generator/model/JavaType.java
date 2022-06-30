package com.chj9.cms.code.generator.model;

import java.math.BigDecimal;
import java.sql.JDBCType;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public enum JavaType {

    BIGDECIMAL(BigDecimal.class, Collections.singletonList(JDBCType.DECIMAL), "大数"),
    BOOLEAN(Boolean.class, Arrays.asList(JDBCType.BIT, JDBCType.BOOLEAN), "布尔值"),
    BYTE(Byte.class, Arrays.asList(JDBCType.BINARY, JDBCType.VARBINARY, JDBCType.LONGVARBINARY), "布尔值"),
    DATE(Instant.class, Arrays.asList(JDBCType.DATE, JDBCType.TIME, JDBCType.TIMESTAMP), "时间"),
    DOUBLE(Double.class, Collections.singletonList(JDBCType.DOUBLE), "双精度浮点数"),
    ENUM(Enum.class, List.of(JDBCType.SMALLINT), "枚举"),
    FLOAT(Float.class, Collections.singletonList(JDBCType.FLOAT), "单精度浮点数"),
    INTEGER(Integer.class, Arrays.asList(JDBCType.TINYINT, JDBCType.INTEGER), "整形"),
    LONG(Long.class, List.of(JDBCType.BIGINT), "长整形"),
    STRING(String.class, Arrays.asList(JDBCType.CHAR, JDBCType.LONGVARCHAR, JDBCType.VARCHAR), "字符串");

    private final Class clazz;
    private final List<JDBCType> jdbcTypes;
    private final String description;

    JavaType(Class clazz, List<JDBCType> jdbcTypes, String description) {
        this.clazz = clazz;
        this.jdbcTypes = jdbcTypes;
        this.description = description;
    }

    public static JavaType valueOf(int value) {
        JDBCType jdbcType = JDBCType.valueOf(value);
        return Arrays.stream(JavaType.values())
            .filter(javaType -> Objects.nonNull(jdbcType) && javaType.getJdbcTypes().contains(jdbcType))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Unknown JDBCType: " + value));
    }

    public Class getClazz() {
        return this.clazz;
    }

    public List<JDBCType> getJdbcTypes() {
        return this.jdbcTypes;
    }

    public String getDescription() {
        return this.description;
    }

}
