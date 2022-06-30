package ${basePackage}.web.${table.bizName}.controller;

import ${basePackage}.domain.entity.${table.bizName}.${table.className}Entity;
import ${basePackage}.service.${table.bizName}.${table.className}Service;
import me.insidezhou.southernquiet.logging.SouthernQuietLogger;
import me.insidezhou.southernquiet.logging.SouthernQuietLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
* ${table.tableComment}视图控制器
*
* <p>Table: ${table.tableName} - ${table.tableComment}</p>
*
* @author ${author}
* @see ${table.className}Entity
* @since ${.now}
*/
@RestController
@RequestMapping("${table.controllerPath}")
public class ${table.className}Controller {
private static final SouthernQuietLogger LOGGER = SouthernQuietLoggerFactory.getLogger(${table.className}Controller.class);

private ${table.className}Service ${table.classInstanceName}Service;

@Autowired
public void set${table.className}Service(${table.className}Service ${table.classInstanceName}Service) {
this.${table.classInstanceName}Service = ${table.classInstanceName}Service;
}

}
