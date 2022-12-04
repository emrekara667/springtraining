package com.etech.springtraining.app.gen.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
@Slf4j
@Order(2)
public class MessageFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("Entered into method doFilter() of class MessageFilter");
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("Before sending response to the client from MessageFilter");
    }
}
