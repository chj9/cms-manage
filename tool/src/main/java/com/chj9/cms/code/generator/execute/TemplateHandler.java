package com.chj9.cms.code.generator.execute;

import com.chj9.cms.code.generator.JavaCodeProperties;
import com.chj9.cms.code.generator.model.Table;
import com.chj9.cms.code.generator.utils.StringUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Component
public class TemplateHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(TemplateHandler.class);

    private Configuration configuration;
    @Autowired
    private JavaCodeProperties javaCodeProperties;

    @PostConstruct
    private void init() throws Exception {
        configuration = new Configuration(Configuration.VERSION_2_3_29);
        configuration.setClassForTemplateLoading(TemplateHandler.class, "/templates");
        configuration.setSetting("default_encoding", String.valueOf(StandardCharsets.UTF_8));
        configuration.setSetting("url_escaping_charset", String.valueOf(StandardCharsets.UTF_8));
        configuration.setSetting("datetime_format", "yyyy-MM-dd hh:mm:ss");
        configuration.setSetting("date_format", "yyyy-MM-dd");
        configuration.setSetting("time_format", "HH:mm:ss");
        configuration.setSetting("number_format", "0.######;");
    }

    private Map<String, Object> buildParams(Table table) {
        Map<String, Object> params = new HashMap<>(8);
        params.put("basePackage", javaCodeProperties.getBasePackage());
        params.put("ignoreProperties", javaCodeProperties.getIgnoreProperties());
        params.put("author", javaCodeProperties.getAuthor());
        params.put("urlPrefix", javaCodeProperties.getUrlPrefix());
        params.put("table", table);
        params.put("uuid", UUID.randomUUID().toString());
        return params;
    }

    private void createFile(Table table, Map<String, Object> params, Template template, String filePath) throws IOException, TemplateException {
        File file = new File(filePath);
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        template.process(params, bw);
        bw.flush();
        fw.flush();
        bw.close();
        fw.close();
        LOGGER.info("【生成文件】表名[{}]路径[{}]", table.getTableName(), filePath);
    }

    public void genController(String targetDic, Table table) throws Exception {
        Map<String, Object> params = buildParams(table);
        Template template = configuration.getTemplate("controller.ftl");
        String dicPath = targetDic + File.separator + "controller" + File.separator + table.getBizName();
        File dic = new File(dicPath);
        if (!dic.exists()) {
            dic.mkdirs();
        }
        String filePath = dicPath + File.separator + table.getClassName() + "Controller.java";
        createFile(table, params, template, filePath);
    }

    public void genServiceInterface(String targetDic, Table table) throws Exception {
        Map<String, Object> params = buildParams(table);
        Template template = configuration.getTemplate("service-interface.ftl");
        String dicPath = targetDic + File.separator + "service" + File.separator + table.getBizName();
        File dic = new File(dicPath);
        if (!dic.exists()) {
            dic.mkdirs();
        }
        String filePath = dicPath + File.separator + table.getClassName() + "Service.java";
        createFile(table, params, template, filePath);
    }

    public void genServiceImpl(String targetDic, Table table) throws Exception {
        Map<String, Object> params = buildParams(table);
        Template template = configuration.getTemplate("service-impl.ftl");
        String dicPath = targetDic + File.separator + "service" + File.separator + table.getBizName() + File.separator + "impl";
        File dic = new File(dicPath);
        if (!dic.exists()) {
            dic.mkdirs();
        }
        String filePath = dicPath + File.separator + table.getClassName() + "ServiceImpl.java";
        createFile(table, params, template, filePath);
    }

    public void genDao(String targetDic, Table table) throws Exception {
        Map<String, Object> params = buildParams(table);
        Template template = configuration.getTemplate("dao.ftl");
        String dicPath = targetDic + File.separator + "dao" + File.separator + table.getBizName();
        File dic = new File(dicPath);
        if (!dic.exists()) {
            dic.mkdirs();
        }
        String filePath = dicPath + File.separator + table.getClassName() + "Dao.java";
        createFile(table, params, template, filePath);
    }

    public void genMapperXml(String targetDic, Table table) throws Exception {
        Map<String, Object> params = buildParams(table);
        Template template = configuration.getTemplate("mapper.ftl");
        String fileName = table.getTableName();
        if (StringUtils.isNotBlank(javaCodeProperties.getStripPrefix())) {
            fileName = fileName.replaceFirst(javaCodeProperties.getStripPrefix(), "");
        }
        fileName = StringUtil.underlineToCamelCase(fileName, true);
        String dicPath = targetDic + File.separator + "mapper" + File.separator + table.getBizName();
        File dic = new File(dicPath);
        if (!dic.exists()) {
            dic.mkdirs();
        }
        String filePath = dicPath + File.separator + fileName + "DaoMapper.xml";
        createFile(table, params, template, filePath);
    }

    public void genIncludeMapperXml(String targetDic, List<Table> tables) throws Exception {
        Table table = tables.get(0);
        Map<String, Object> params = buildParams(table);
        params.put("tables", tables);
        Template template = configuration.getTemplate("include-mapper.ftl");
        String dicPath = targetDic + File.separator + "mapper" + File.separator + "commons";
        File dic = new File(dicPath);
        if (!dic.exists()) {
            dic.mkdirs();
        }
        String filePath = dicPath + File.separator + "include-mapper.xml";
        createFile(table, params, template, filePath);
    }

    public void genEntity(String targetDic, Table table) throws Exception {
        Map<String, Object> params = buildParams(table);
        Template template = configuration.getTemplate("entity.ftl");
        String dicPath = targetDic + File.separator + "domain" + File.separator + "entity" + File.separator + table.getBizName();
        File dic = new File(dicPath);
        if (!dic.exists()) {
            dic.mkdirs();
        }
        String filePath = dicPath + File.separator + table.getClassName() + "Entity.java";
        createFile(table, params, template, filePath);
    }

    public void genEntityVO(String targetDic, Table table) throws Exception {
        Map<String, Object> params = buildParams(table);
        Template template = configuration.getTemplate("entityVo.ftl");
        String dicPath = targetDic + File.separator + "domain" + File.separator + "vo" + File.separator + table.getBizName();
        File dic = new File(dicPath);
        if (!dic.exists()) {
            dic.mkdirs();
        }
        String filePath = dicPath + File.separator + table.getClassName() + "Vo.java";
        createFile(table, params, template, filePath);
    }

    public void genSearchVO(String targetDic, Table table) throws Exception {
        Map<String, Object> params = buildParams(table);
        Template template = configuration.getTemplate("SearchVo.ftl");
        String dicPath = targetDic + File.separator + "domain" + File.separator + "vo" + File.separator + table.getBizName() + File.separator + "search";
        File dic = new File(dicPath);
        if (!dic.exists()) {
            dic.mkdirs();
        }
        String filePath = dicPath + File.separator + table.getClassName() + "SearchVo.java";
        createFile(table, params, template, filePath);
    }

    public void genDoc(String targetDic, Table table) throws Exception {
        Map<String, Object> params = buildParams(table);
        Template template = configuration.getTemplate("doc.ftl");
        String dicPath = targetDic + File.separator + "doc";
        File dic = new File(dicPath);
        if (!dic.exists()) {
            dic.mkdirs();
        }
        String filePath = dicPath + File.separator + table.getClassName() + "Doc.json";
        createFile(table, params, template, filePath);
    }

}
