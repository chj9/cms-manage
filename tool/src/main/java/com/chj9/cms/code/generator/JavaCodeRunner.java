package com.chj9.cms.code.generator;
import com.chj9.cms.code.generator.execute.TableHandler;
import com.chj9.cms.code.generator.execute.TemplateHandler;
import com.chj9.cms.code.generator.model.Table;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.io.File;
import java.util.List;

public class JavaCodeRunner implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(JavaCodeRunner.class);

    @Autowired
    private TableHandler tableHandler;
    @Autowired
    private TemplateHandler templateHandler;

    @Autowired
    private JavaCodeProperties javaCodeProperties;

    @Override
    public void run(String... args) throws Exception {

        StringBuilder targetDic = new StringBuilder();
        targetDic.append(System.getProperty("user.dir"));
        targetDic.append(File.separator).append("output");

        //删除输出文件夹
        File dic = new File(targetDic.toString());
        if (dic.exists() && dic.isDirectory()) {
            FileUtils.deleteDirectory(dic);
        }

        targetDic.append(File.separator).append("src");
        targetDic.append(File.separator).append("main");
        //targetDic.append(File.separator);

        String baseDic = javaCodeProperties.getBasePackage().replace(".", File.separator);
        StringBuilder javaDic = new StringBuilder(targetDic.toString());
        javaDic.append(File.separator).append("java").append(File.separator).append(baseDic);

        StringBuilder resDic = new StringBuilder(targetDic.toString());
        resDic.append(File.separator).append("resources");

        try {
            List<Table> tables = tableHandler.getTables(javaCodeProperties.getTableNames());
            for (Table table : tables) {
                // 生成实体类
                if (javaCodeProperties.isGenEntity()) {
                    templateHandler.genEntity(javaDic.toString(), table);
                    templateHandler.genEntityVO(javaDic.toString(), table);
                }

                // 生成数据层
                if (javaCodeProperties.isGenDao()) {
                    templateHandler.genDao(javaDic.toString(), table);
                }

                // 生成 XML
                if (javaCodeProperties.isGenMapperXml()) {
                    templateHandler.genMapperXml(resDic.toString(), table);
                    /*includeMapperTableList.add(table);*/
                }

                // 生成Service
                /*if (javaCodeProperties.isGenService()) {
                      templateHandler.genService(javaDic.toString(), table);
                }*/

                if (javaCodeProperties.isGenServiceInterface()) {
                    templateHandler.genServiceInterface(javaDic.toString(), table);
                    templateHandler.genServiceImpl(javaDic.toString(), table);
                }

                // 生成Controller
                if (javaCodeProperties.isGenController()) {
                    templateHandler.genController(javaDic.toString(), table);
                    templateHandler.genSearchVO(javaDic.toString(), table);
                }

                // 生成Doc
                if (javaCodeProperties.isGenDoc()) {
                    templateHandler.genDoc(resDic.toString(), table);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
