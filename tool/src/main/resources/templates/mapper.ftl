<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${basePackage}.dao.${table.bizName}.${table.className}Dao" >
    <resultMap id="baseResultMap" type="${basePackage}.domain.entity.${table.bizName}.${table.className}Entity" >
    <#list table.columns as column>
        <#if column.primaryKeyFlag == true>
        <id column="${column.columnName}" jdbcType="${column.jdbcTypeName}" property="${column.javaVarName}"/>
        <#else>
        <result column="${column.columnName}" jdbcType="${column.jdbcTypeName}" property="${column.javaVarName}" <#if column.jdbcTypeName == "LONGVARCHAR">typeHandler="com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler"</#if>/>
        </#if>
    </#list>
    </resultMap>

    <sql id="baseColumns">
        <#list table.columns as column>
        ${column.columnName}<#if column_has_next>,</#if>
        </#list>
    </sql>

</mapper>
