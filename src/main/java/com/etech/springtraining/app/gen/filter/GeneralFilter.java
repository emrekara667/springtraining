package com.etech.springtraining.app.gen.filter;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Component
@Slf4j
@Order(1)
public class GeneralFilter implements Filter {

    private static final ThreadLocal<String> R_HOLDER = new ThreadLocal<>();



    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        log.info("Entered into method doFilter() of class GeneralFilter");
        System.out.println("Before sending request to the controller");
        System.out.println("Remote host = " + servletRequest.getRemoteHost());
        System.out.println("Protocol = " + servletRequest.getProtocol());
        System.out.println("Remote address = " + servletRequest.getRemoteAddr());
        System.out.println("Local port = " + servletRequest.getLocalPort());
        System.out.println("Server name = " + servletRequest.getServerName());

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        System.out.println("Method name : " + httpServletRequest.getMethod());
        System.out.println("Method URI : " + httpServletRequest.getRequestURI());
        System.out.println("Method PATH : " + httpServletRequest.getServletPath());


        String xId = httpServletRequest.getHeader("X-request-id");
        xId = StringUtils.isEmpty(xId) ? UUID.randomUUID().toString() : xId;
        R_HOLDER.set(xId);


        filterChain.doFilter(servletRequest, servletResponse);


        log.info("Before sending response to the client from GeneralFilter");
    }

    public static String rGetter() {
        return R_HOLDER.get();
    }
}
