package com.chj9.cms.web.config;

import com.chj9.cms.web.filter.XssFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableConfigurationProperties
public class XSSFilterConfiguration {

    public static final String XSSFilterConfigKeyRoots = "xss.filter";

    @Bean
    @ConfigurationProperties(XSSFilterConfigKeyRoots)
    public XSSFilterProperties xssFilterProperties() {
        return new XSSFilterProperties();
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Bean
    //@ConditionalOnMissingBean
    public FilterRegistrationBean xssFilterRegistration(XSSFilterProperties xssFilterProperties) {
        String enabled = StringUtils.trimToEmpty(xssFilterProperties.getEnabled());
        String patterns = StringUtils.trimToEmpty(xssFilterProperties.getPatterns());
        String excludes = StringUtils.trimToEmpty(xssFilterProperties.getExcludes());
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new XssFilter());
        registration.addUrlPatterns(StringUtils.split(patterns, ","));
        registration.setName("xssFilter");
        registration.setOrder(FilterRegistrationBean.HIGHEST_PRECEDENCE);
        Map<String, String> initParameters = new HashMap<>();
        initParameters.put("enabled", enabled);
        initParameters.put("excludes", excludes);
        registration.setInitParameters(initParameters);
        return registration;
    }

    public static class XSSFilterProperties {

        private String enabled = "false";

        private String excludes;

        private String patterns;

        public String getEnabled() {
            return enabled;
        }

        public void setEnabled(String enabled) {
            this.enabled = enabled;
        }

        public String getExcludes() {
            return excludes;
        }

        public void setExcludes(String excludes) {
            this.excludes = excludes;
        }

        public String getPatterns() {
            return patterns;
        }

        public void setPatterns(String patterns) {
            this.patterns = patterns;
        }

    }

}
