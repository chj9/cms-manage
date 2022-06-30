package com.chj9.cms.code.generator.execute;

import com.chj9.cms.code.generator.JavaCodeProperties;
import com.chj9.cms.code.generator.model.Column;
import com.chj9.cms.code.generator.model.JavaType;
import com.chj9.cms.code.generator.model.Table;
import com.chj9.cms.code.generator.utils.StringUtil;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
public class TableHandler implements DisposableBean {

    private final static Logger LOGGER = LoggerFactory.getLogger(TableHandler.class);

    private Connection connection;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JavaCodeProperties javaCodeProperties;

    public TableHandler() {
    }

    public TableHandler(Connection connection, DataSource dataSource, JavaCodeProperties javaCodeProperties) {
        this.connection = connection;
        this.dataSource = dataSource;
        this.javaCodeProperties = javaCodeProperties;
    }

    @PostConstruct
    public void init() throws Exception {
        this.connection = dataSource.getConnection();
    }

    private String getTableComment(String tableName) {
        String result = "";
        try {
            String TABLE_COMMENT_SQL = "SHOW TABLE STATUS WHERE NAME = ?";
            PreparedStatement statement = connection.prepareStatement(TABLE_COMMENT_SQL);
            statement.setString(1, tableName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String TABLE_COMMENT_COLUMN = "COMMENT";
                result = resultSet.getString(TABLE_COMMENT_COLUMN);
            }
            resultSet.close();
            statement.close();
            if (StringUtils.isBlank(result)) {
                throw new RuntimeException("Table [" + tableName + " ] must provide a comment!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Table> getTables(List<String> tableNames) {
        try {
            ResultSet resultSet = connection.getMetaData().getTables(javaCodeProperties.getCatalog(), javaCodeProperties.getSchemaName(), "%", new String[]{"TABLE", "VIEW"});
            List<String> tables = new ArrayList<>();
            while (resultSet.next()) {
                tables.add(resultSet.getString("TABLE_NAME"));
            }
            if (CollectionUtils.isEmpty(tables)) {
                resultSet.close();
                throw new RuntimeException("获取不到任何表信息");
            }
            if (CollectionUtils.isEmpty(tableNames)) {
                tableNames = tables;
            }
            return tableNames.stream()
                .map(
                    tableName -> {
                        if (tableName.equalsIgnoreCase("ID_GENERATOR_WORKER")) {
                            return null;
                        }
                        if (StringUtil.isBlank(tableName) || !tableName.startsWith(javaCodeProperties.getStripPrefix())) {
                            throw new RuntimeException("提取所属业务模块名称异常，tableName：" + tableName);
                        }
                        if (!tables.contains(tableName)) {
                            throw new RuntimeException("无法在数据库匹配到：" + tableName + " 数据表，请检查所链接的数据库以及指定的表名拼写是否正确。");
                        }
                        //获取主键
                        List<String> primaryKeys = getPrimaryKeys(tableName);
                        if (CollectionUtils.isEmpty(primaryKeys)) {
                            LOGGER.error("表[{}]找不到主键", tableName);
                            try {
                                resultSet.close();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            return null;
                        }

                        Table table = new Table();
                        table.setTableName(tableName);
                        String bizName = tableName.replaceFirst(javaCodeProperties.getStripPrefix(), "").split("_")[0];
                        table.setBizName(bizName);

                        String className = tableName;
                        if (StringUtils.isNotBlank(javaCodeProperties.getStripPrefix())) {
                            className = RegExUtils.replaceFirst(className, javaCodeProperties.getStripPrefix(), "");
                        }

                        table.setClassName(StringUtil.underlineToCamelCase(className, true));
                        table.setClassInstanceName(StringUtil.underlineToCamelCase(className));

                        table.setColumns(getTableColumns(primaryKeys, tableName));
                        table.setTableComment(getTableComment(tableName));
                        table.setControllerPath(StringUtil.replace(className, "_", "/"));
                        table.setPluginName(className);

                        for (Column column : table.getColumns()) {
                            if (column.isPrimaryKeyFlag()) {
                                table.setPrimaryKeyJavaTypeName(column.getJavaTypeName());
                                break;
                            }
                        }
                        return table;
                    })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public List<String> getPrimaryKeys(String tableName) {
        List<String> primaryKeys = new ArrayList<>();
        try {
            // 获取表内的主键列表
            ResultSet resultSet = connection.getMetaData().getPrimaryKeys(javaCodeProperties.getCatalog(), javaCodeProperties.getSchemaName(), tableName);
            while (resultSet.next()) {
                // 获取主键的列名
                String pkColumnName = resultSet.getString("COLUMN_NAME");
                if (primaryKeys.contains(pkColumnName)) {
                    continue;
                }
                primaryKeys.add(pkColumnName);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return primaryKeys;
    }

    private List<Column> getTableColumns(List<String> primaryKeys, String tableName) {
        List<Column> columns = new ArrayList<>();
        try {
            // 获取列信息
            ResultSet resultSet = connection.getMetaData().getColumns(javaCodeProperties.getCatalog(), javaCodeProperties.getSchemaName(), tableName, "%");
            while (resultSet.next()) {
                Column column = getTableColumn(resultSet, primaryKeys);
                if (Objects.isNull(column)) {
                    continue;
                }
                if (columns.stream().anyMatch(c -> c.getColumnName().equals(column.getColumnName()))) {
                    continue;
                }
                columns.add(column);
            }
            resultSet.close();
            return columns;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columns;
    }

    private Column getTableColumn(ResultSet rs, List<String> primaryKeys) {
        try {
            Column column = new Column();
            // 数据库字段类型
            int jdbcTypeInt = rs.getInt("DATA_TYPE");

            JDBCType jdbcType = JDBCType.valueOf(jdbcTypeInt);
            JavaType javaType = JavaType.valueOf(jdbcTypeInt);
            // 列名
            String columnName = rs.getString("COLUMN_NAME");

            column.setJavaName(StringUtil.underlineToCamelCase(columnName, true));
            column.setJavaVarName(StringUtil.underlineToCamelCase(columnName));
            column.setJavaTypeName(javaType.getClazz().getSimpleName());

            boolean primaryKeyFlag = primaryKeys.contains(columnName);

            // 表名
            String tableName = rs.getString("TABLE_NAME");
            column.setPrimaryKeyFlag(primaryKeyFlag);
            column.setColumnName(columnName);
            column.setJdbcType(jdbcTypeInt);
            column.setJdbcTypeName(jdbcType.getName());
            column.setJavaType(javaType);

            column.setSize(rs.getInt("COLUMN_SIZE"));
            column.setNullable(rs.getBoolean("NULLABLE"));
            column.setDefaultValue(rs.getString("COLUMN_DEF"));
            String comment = rs.getString("REMARKS");
            if (StringUtils.isBlank(comment)) {
                throw new RuntimeException("Table [" + tableName + " ] 's field [" + columnName + "] must provide a comment!");
            }
            column.setColumnComment(rs.getString("REMARKS"));
            column.setAutoincrement(hasColumn(rs, "IS_AUTOINCREMENT") && BooleanUtils.toBoolean(rs.getString("IS_AUTOINCREMENT").toLowerCase()));
            try (PreparedStatement pc = connection.prepareStatement("SHOW INDEX FROM " + tableName + " WHERE non_unique = 0 AND key_name != 'PRIMARY' AND column_name = '" + columnName + "'")) {
                try (ResultSet executeQuery = pc.executeQuery()) {
                    if (executeQuery.next()) {
                        if (!executeQuery.getBoolean("non_unique")) {
                            column.setUnique(true);
                        }
                    }
                }
            }
            return column;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 结果集内是否存在自增的列
     *
     * @param rs         结果集
     * @param columnName 自增列名
     * @return true：存在列表，false：不存在列
     * @throws SQLException 数据库异常
     */
    private boolean hasColumn(ResultSet rs, String columnName) throws SQLException {
        ResultSetMetaData resultSetMetaData = rs.getMetaData();
        int columns = resultSetMetaData.getColumnCount();
        for (int x = 1; x <= columns; x++) {
            if (columnName.equals(resultSetMetaData.getColumnName(x))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void destroy() throws Exception {
        if (Objects.nonNull(connection)) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
