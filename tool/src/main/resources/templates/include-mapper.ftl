<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${basePackage}.support.Include" >

    <#list tables as table>
    <!-- ${table.tableName}[${table.tableComment}]列表查询条件 -->
    <sql id="${table.classInstanceName}SelectCondition">
        <where>
            <trim suffixOverrides="AND">
            <#list table.columns as column>
                <#if column.unique>
                <if test="${column.javaVarName}Eq != null and !''.equals(${column.javaVarName}Eq)">
                    AND ${column.columnName} = ${r'#{'}${column.javaVarName}Eq}
                </if>
                </#if>
            </#list>
            </trim>
        </where>
    </sql>

    </#list>

</mapper>