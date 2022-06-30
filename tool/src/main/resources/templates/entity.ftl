<#macro getPrefix javaTypeName=""><#if javaTypeName == "Boolean">is<#else>get</#if></#macro>
package ${basePackage}.domain.entity.${table.bizName};

import com.baomidou.mybatisplus.annotation.*;
import ${basePackage}.datasource.id.IdEntity;
import org.apache.ibatis.type.Alias;

<#if table.hasBigDecimal()>
import java.math.BigDecimal;
</#if>
<#if table.hasDate()>
import java.time.Instant;
</#if>

/**
 * <p>Table: ${table.tableName} - ${table.tableComment}</p>
 *
 * @author ${author}
 */
@Alias("${table.classInstanceName}")
@TableName(value = "${table.tableName}", autoResultMap = true)
public class ${table.className}Entity extends IdEntity {

    <#list table.columns as column>
    <#if ignoreProperties?seq_contains(column.javaVarName) == false>
    /**
     * ${column.columnComment}
     */
    private ${column.javaTypeName} ${column.javaVarName};
    </#if>
    </#list>
    <#list table.columns as column>
    <#if ignoreProperties?seq_contains(column.javaVarName) == false>

    public ${column.javaTypeName} get${column.javaName}() {
        return this.${column.javaVarName};
    }

    public void set${column.javaName}(${column.javaTypeName} ${column.javaVarName}) {
        this.${column.javaVarName} =  ${column.javaVarName};
    }
    </#if>
    </#list>

}
