package com.chj9.cms.code.generator;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(JavaCodeProperties.class)
public class JavaCodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaCodeApplication.class, args);
    }

    @Bean
    public JavaCodeRunner javaCodeRunner() {
        return new JavaCodeRunner();
    }

}
