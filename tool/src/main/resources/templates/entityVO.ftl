<#macro getPrefix javaTypeName=""><#if javaTypeName == "Boolean">is<#else>get</#if></#macro>
package ${basePackage}.domain.${table.bizName};

import java.io.Serializable;
<#if table.hasBigDecimal()>
    import java.math.BigDecimal;
</#if>
<#if table.hasDate()>
    import java.time.Instant;
</#if>

/**
 * <p>${table.tableComment} VO</p>
 *
 * @author ${author}
 */
public class ${table.className}Vo implements Serializable {

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
