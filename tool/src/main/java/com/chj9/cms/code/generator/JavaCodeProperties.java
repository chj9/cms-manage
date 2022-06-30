package com.chj9.cms.code.generator;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "jcode")
public class JavaCodeProperties {

    private String catalog;
    private String schemaName;

    private String author;

    /**
     * 包名
     */
    private String basePackage;

    private List<String> ignoreProperties = new ArrayList<>();

    /**
     * 生成表名
     */
    private List<String> tableNames;

    /**
     * 忽略前缀
     */
    private String stripPrefix;

    /**
     * 生成控制器
     */
    private boolean genController = false;

    /**
     * 生成服务
     */
    private boolean genService = false;

    /**
     * 生成带接口的服务
     */
    private boolean genServiceInterface = false;

    /**
     * 生成实体类
     */
    private boolean genEntity = false;

    /**
     * 生成数据层
     */
    private boolean genDao = false;
    /**
     * 生成文档
     */
    private boolean genDoc = false;
    /**
     * 需要生成的接口
     */
    private List<String> genMethods;
    /**
     * url 前缀
     */
    private String urlPrefix;

    /**
     * 生成xml
     */
    private boolean genMapperXml = false;

    public JavaCodeProperties() {
    }

    public String getCatalog() {
        return this.catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getSchemaName() {
        return this.schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public List<String> getIgnoreProperties() {
        return ignoreProperties;
    }

    public void setIgnoreProperties(List<String> ignoreProperties) {
        this.ignoreProperties = ignoreProperties;
    }

    public List<String> getTableNames() {
        return tableNames;
    }

    public void setTableNames(List<String> tableNames) {
        this.tableNames = tableNames;
    }

    public String getStripPrefix() {
        return stripPrefix;
    }

    public void setStripPrefix(String stripPrefix) {
        this.stripPrefix = stripPrefix;
    }

    public boolean isGenController() {
        return genController;
    }

    public void setGenController(boolean genController) {
        this.genController = genController;
    }

    public boolean isGenService() {
        return genService;
    }

    public void setGenService(boolean genService) {
        this.genService = genService;
    }

    public boolean isGenEntity() {
        return genEntity;
    }

    public void setGenEntity(boolean genEntity) {
        this.genEntity = genEntity;
    }

    public boolean isGenDao() {
        return genDao;
    }

    public void setGenDao(boolean genDao) {
        this.genDao = genDao;
    }

    public boolean isGenMapperXml() {
        return genMapperXml;
    }

    public void setGenMapperXml(boolean genMapperXml) {
        this.genMapperXml = genMapperXml;
    }

    public boolean isGenDoc() {
        return genDoc;
    }

    public void setGenDoc(boolean genDoc) {
        this.genDoc = genDoc;
    }

    public List<String> getGenMethods() {
        return this.genMethods;
    }

    public void setGenMethods(List<String> genMethods) {
        this.genMethods = genMethods;
    }

    public String getUrlPrefix() {
        return urlPrefix;
    }

    public void setUrlPrefix(String urlPrefix) {
        this.urlPrefix = urlPrefix;
    }

    public boolean isGenServiceInterface() {
        return genServiceInterface;
    }

    public void setGenServiceInterface(boolean genServiceInterface) {
        this.genServiceInterface = genServiceInterface;
    }
}
