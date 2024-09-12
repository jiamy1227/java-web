package com.jiamy.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class LogFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // System.out.println("LogFilter: process "+ ((HttpServletRequest) servletRequest).getRequestURI());
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
