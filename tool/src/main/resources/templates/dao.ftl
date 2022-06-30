package ${basePackage}.dao.${table.bizName};

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ${basePackage}.domain.entity.${table.bizName}.${table.className}Entity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * ${table.tableComment}数据访问层
 *
 * <p>Table: ${table.tableName} - ${table.tableComment}</p>
 *
 * @author ${author}
 * @see ${table.className}Entity
 */
@Mapper
@Repository
public interface ${table.className}Dao extends BaseMapper<${table.className}Entity> {

}
