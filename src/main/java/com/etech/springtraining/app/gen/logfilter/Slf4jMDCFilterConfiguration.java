package com.etech.springtraining.app.gen.logfilter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Slf4jMDCFilterConfiguration {
    @Bean
    public FilterRegistrationBean<Slf4jMDCFilter> servletRegistrationBean() {
        final FilterRegistrationBean<Slf4jMDCFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new Slf4jMDCFilter());
        registrationBean.setOrder(2);
        return registrationBean;
    }
}
