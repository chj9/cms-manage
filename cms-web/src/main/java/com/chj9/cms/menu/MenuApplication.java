package com.chj9.cms.menu;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

//@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
@EnableAspectJAutoProxy(proxyTargetClass = true)
@SpringBootApplication(
        scanBasePackages = "com.chj9.cms"
)
@MapperScan(
        basePackages = "com.chj9.cms.dao.mapper"
)
public class MenuApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MenuApplication.class);
        ApplicationPidFileWriter pidFileWriter = new ApplicationPidFileWriter();
        pidFileWriter.setTriggerEventType(ApplicationReadyEvent.class);
        app.addListeners(pidFileWriter);
        app.run(args);
    }

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        // 1、需要先定义一个converter 转换器
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        // 2、添加fastJson 的配置信息，比如：是否要格式化返回的json数据
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        //处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        // 3、在convert 中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);
        // 4、将convert 添加到converters当中
        return new HttpMessageConverters(fastConverter);
    }
}
