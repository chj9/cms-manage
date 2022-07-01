package com.chj9.cms;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AdminApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(AdminApplication.class);
        ApplicationPidFileWriter pidFileWriter = new ApplicationPidFileWriter();
        pidFileWriter.setTriggerEventType(ApplicationReadyEvent.class);
        app.addListeners(pidFileWriter);
        app.run(args);
    }


}
