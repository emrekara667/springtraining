package com.etech.springtraining.app.gen.logfilter;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
@Component
@RequiredArgsConstructor
public class Slf4jMDCFilter extends OncePerRequestFilter {


    public static final String DEFAULT_RESPONSE_TOKEN_HEADER = "Response_Token";
    public static final String DEFAULT_MDC_UUID_TOKEN_KEY = "Slf4jMDCFilter.UUID";

    private String responseHeader = DEFAULT_RESPONSE_TOKEN_HEADER;
    private String mdcTokenKey = DEFAULT_MDC_UUID_TOKEN_KEY;
    private String requestHeader = "X-request-id";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

       try {
           final String token;
           if(StringUtils.isEmpty(requestHeader) && !StringUtils.isEmpty(request.getHeader(requestHeader))){
               token = request.getHeader(requestHeader);
           }else{
               token = UUID.randomUUID().toString().toUpperCase().replace("-", "");
           }
           MDC.put(mdcTokenKey, token);
           if(!StringUtils.isEmpty(responseHeader)){
               response.addHeader(requestHeader, token);
           }
           filterChain.doFilter(request, response);
       }finally {
           MDC.remove(mdcTokenKey);
       }

    }
}
