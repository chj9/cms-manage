package com.chj9.cms.web.config;

import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

public class WebConfig extends WebMvcConfigurationSupport {


	@Override
	protected void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").allowCredentials(true).allowedMethods("POST", "GET", "PUT",
				"DELETE", "OPTIONS");
	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		// 后缀匹配
		configurer.favorPathExtension(false);
	}
	

}
