package com.chj9.cms.code.generator.model;

public class Column {

    /**
     * 列名
     */
    private String columnName;

    /**
     * 是否为主键
     */
    private boolean primaryKeyFlag;
    /**
     * 字段注释
     */
    private String columnComment;
    /**
     * 数据库类型
     */
    private int jdbcType;

    /**
     * 数据库类型名称
     */
    private String jdbcTypeName;

    /**
     * java类型
     */
    private JavaType javaType;

    /**
     * 类型名称
     */
    private String javaTypeName;

    /**
     * java名称
     */
    private String javaName;

    /**
     * java变量名称
     */
    private String javaVarName;

    /**
     * 列长度
     */
    private int size;
    /**
     * 小数点位数
     */
    private int decimalDigits;
    /**
     * 是否为空
     */
    private boolean nullable;
    /**
     * 是否自增
     */
    private boolean autoincrement;
    /**
     * 默认值
     */
    private String defaultValue;
    /**
     * 是否唯一索引
     */
    private boolean unique;

    public Column() {
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public boolean isPrimaryKeyFlag() {
        return primaryKeyFlag;
    }

    public void setPrimaryKeyFlag(boolean primaryKeyFlag) {
        this.primaryKeyFlag = primaryKeyFlag;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public int getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(int jdbcType) {
        this.jdbcType = jdbcType;
    }

    public String getJdbcTypeName() {
        return jdbcTypeName;
    }

    public void setJdbcTypeName(String jdbcTypeName) {
        this.jdbcTypeName = jdbcTypeName;
    }

    public JavaType getJavaType() {
        return javaType;
    }

    public void setJavaType(JavaType javaType) {
        this.javaType = javaType;
    }

    public String getJavaTypeName() {
        return javaTypeName;
    }

    public void setJavaTypeName(String javaTypeName) {
        this.javaTypeName = javaTypeName;
    }

    public String getJavaName() {
        return javaName;
    }

    public void setJavaName(String javaName) {
        this.javaName = javaName;
    }

    public String getJavaVarName() {
        return javaVarName;
    }

    public void setJavaVarName(String javaVarName) {
        this.javaVarName = javaVarName;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getDecimalDigits() {
        return decimalDigits;
    }

    public void setDecimalDigits(int decimalDigits) {
        this.decimalDigits = decimalDigits;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public boolean isAutoincrement() {
        return autoincrement;
    }

    public void setAutoincrement(boolean autoincrement) {
        this.autoincrement = autoincrement;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }
}
