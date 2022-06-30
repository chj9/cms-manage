package ${basePackage}.service.${table.bizName}.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ${basePackage}.dao.${table.bizName}.${table.className}Dao;
import ${basePackage}.domain.entity.${table.bizName}.${table.className}Entity;
import ${basePackage}.service.${table.bizName}.${table.className}Service;
import me.insidezhou.southernquiet.logging.SouthernQuietLogger;
import me.insidezhou.southernquiet.logging.SouthernQuietLoggerFactory;
import org.springframework.stereotype.Service;

/**
 * ${table.tableComment}业务逻辑实现
 *
 * <p>Table: ${table.tableName} - ${table.tableComment}</p>
 *
 * @author ${author}
 * @see ${table.className}Entity
 */
@Service
public class ${table.className}ServiceImpl extends ServiceImpl<${table.className}Dao, ${table.className}Entity> implements ${table.className}Service {
    private static final SouthernQuietLogger LOGGER = SouthernQuietLoggerFactory.getLogger(${table.className}ServiceImpl.class);

}
