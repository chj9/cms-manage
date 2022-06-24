package com.dliberty.cms.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.IFileCreate;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.FileType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

public class CodeGenerator {

	public static void main(String[] args) {

		/*
		 * ----------------------------- dao层代码生成配置
		 * -----------------------------
		 */
		String url = "jdbc:mysql://127.0.0.1:3306/food?useUnicode=true&characterEncoding=utf8&useSSL=true&verifyServerCertificate=false";
		String driver = "com.mysql.jdbc.Driver";

		String projectPath = System.getProperty("user.dir"); // 项目路径 （注意后面拼接
																// module 名称）
		String entityPackage = "com.dliberty.cms.dao.entity"; // entity 文件生成包路径
		String mapperPackage = "com.dliberty.cms.dao.mapper"; // mapper 文件生成包路径
		String xmlPath = System.getProperty("user.dir") + "\\src\\main\\resources\\mapper"; // xml
																							// 文件生成位置
		String[] targetTables = { "cms_menu_label_relation" }; // 针对哪些表生成,null表示生成所有
		/*
		 * ----------------------------- end -----------------------------
		 */

		AutoGenerator mpg = new AutoGenerator();
		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		gc.setOutputDir(projectPath + "/src/main/java");
		gc.setAuthor("LG");
		gc.setOpen(false);
		gc.setFileOverride(false); // 不覆盖已经存在的文件
		gc.setIdType(IdType.AUTO);

		// xml
		gc.setBaseColumnList(true);
		gc.setBaseResultMap(true);

		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setUrl(url);
		dsc.setDbType(DbType.MYSQL);
		// dsc.setSchemaName("public");
		dsc.setDriverName(driver);
		dsc.setUsername("root");
	    dsc.setPassword("Mysql1234!@#");

		mpg.setDataSource(dsc);

		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setParent("com");
		pc.setModuleName("dliberty");
		pc.setEntity(entityPackage.replaceAll("^com\\.dliberty\\.", ""));
		pc.setMapper(mapperPackage.replaceAll("^com\\.dliberty\\.", ""));
		pc.setController(entityPackage.replaceAll("^com\\.dliberty\\.", ""));
		pc.setService(mapperPackage.replaceAll("^com\\.dliberty\\.", ""));
		pc.setServiceImpl(mapperPackage.replaceAll("^com\\.dliberty\\.", ""));

		mpg.setPackageInfo(pc);

		// 自定义配置
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
				// to do nothing

			}
		};
		cfg.setFileCreate(new IFileCreate() {
			@Override
			public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
				return true;
			}
		});

		List<FileOutConfig> focList = new ArrayList<>();
		File xmlDir = new File(xmlPath);
		if (!xmlDir.exists()) {
			xmlDir.mkdirs();
		}
		focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
			@Override
			public String outputFile(TableInfo tableInfo) {

				// 自定义输入文件名称
				return xmlPath + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
			}
		});
		cfg.setFileOutConfigList(focList);
		mpg.setCfg(cfg);
		mpg.setTemplate(new TemplateConfig().setXml(null));

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setNaming(NamingStrategy.underline_to_camel); // 表名 下划线转驼峰
		strategy.setColumnNaming(NamingStrategy.underline_to_camel); // 列名
																		// 下划线转驼峰
		// strategy.setSuperEntityClass("com.onelink.framework.common.dao.entity.base.BaseEntity");
		strategy.setSuperMapperClass("com.dliberty.cms.dao.base.BaseMapper");
		strategy.setEntityLombokModel(false); // 使用lombok
		strategy.setInclude(targetTables);

		/*
		 * 放在 BaseEntity的字段
		 */
		// strategy.setSuperEntityColumns("id", "is_deleted", "create_time",
		// "create_user_id", "update_time", "update_user_id");
		mpg.setStrategy(strategy);
		mpg.setTemplateEngine(new FreemarkerTemplateEngine());
		mpg.execute();
	}

}
