package com.chj9.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class MenuApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MenuApplication.class);
        ApplicationPidFileWriter pidFileWriter = new ApplicationPidFileWriter();
        pidFileWriter.setTriggerEventType(ApplicationReadyEvent.class);
        app.addListeners(pidFileWriter);
        app.run(args);
    }
}
