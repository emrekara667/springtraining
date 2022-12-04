package com.etech.springtraining.app.gen.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {
    @Bean
    public FilterRegistrationBean<ActuatorFilter> registrationBean(){
        FilterRegistrationBean<ActuatorFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new ActuatorFilter());
        registrationBean.addUrlPatterns("/actuator/*");
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<MessageFilter> registrationBeanTwo(){
        FilterRegistrationBean<MessageFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new MessageFilter());
        registrationBean.addUrlPatterns("/actuator/*");
        return registrationBean;
    }
}
