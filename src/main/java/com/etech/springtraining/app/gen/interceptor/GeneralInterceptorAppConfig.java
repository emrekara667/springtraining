package com.etech.springtraining.app.gen.interceptor;

import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


@RequiredArgsConstructor
//@Component
public class GeneralInterceptorAppConfig extends WebMvcConfigurationSupport {

   /* private final GeneralInterceptor generalInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(generalInterceptor).order(Ordered.LOWEST_PRECEDENCE);
    }*/
}
