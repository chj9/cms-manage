package com.chj9.cms.code.generator.model;

import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

public class Table {

    /**
     * 表名称
     */
    private String tableName;
    /**
     * 类名
     */
    private String className;
    /**
     * 实例名称
     */
    private String classInstanceName;
    /**
     * 表注释
     */
    private String tableComment;
    /**
     * 主键java类型名称
     */
    private String primaryKeyJavaTypeName;
    /**
     * 控制器路径
     */
    private String controllerPath;
    /**
     * 插件名称
     */
    private String pluginName;

    /**
     * 所属业务模块名
     */
    private String bizName;

    /**
     * 列
     */
    private List<Column> columns;

    public Table() {
    }

    public boolean hasDate() {
        return !CollectionUtils.isEmpty(columns) && columns.stream().anyMatch(column -> Objects.equals(JavaType.DATE, column.getJavaType()));
    }

    public boolean hasBigDecimal() {
        return !CollectionUtils.isEmpty(columns) && columns.stream().anyMatch(column -> Objects.equals(JavaType.BIGDECIMAL, column.getJavaType()));
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassInstanceName() {
        return classInstanceName;
    }

    public void setClassInstanceName(String classInstanceName) {
        this.classInstanceName = classInstanceName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public String getPrimaryKeyJavaTypeName() {
        return primaryKeyJavaTypeName;
    }

    public void setPrimaryKeyJavaTypeName(String primaryKeyJavaTypeName) {
        this.primaryKeyJavaTypeName = primaryKeyJavaTypeName;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public String getControllerPath() {
        return controllerPath;
    }

    public void setControllerPath(String controllerPath) {
        this.controllerPath = controllerPath;
    }

    public String getPluginName() {
        return pluginName;
    }

    public void setPluginName(String pluginName) {
        this.pluginName = pluginName;
    }

    public String getBizName() {
        return bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
    }

}
