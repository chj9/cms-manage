<#macro getPrefix javaTypeName=""><#if javaTypeName == "Boolean">is<#else>get</#if></#macro>
package ${basePackage}.domain.${table.bizName};

import java.io.Serializable;

/**
* <p>${table.tableComment} SearchVO</p>
*
* @author ${author}
*/
public class ${table.className}SearchVo implements Serializable {

}
