package com.zkn.learnspringboot.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 过滤器Demo
 * @author: lxh
 * @create: 2018-07-26 10:24
 **/
@WebFilter(urlPatterns = "/*")
public class DemoFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("开始过滤");

        // xss过滤
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        CrossScriptingRequestWrapper xssRequest = new CrossScriptingRequestWrapper(request);
        filterChain.doFilter(xssRequest, response);
        System.out.println("结束过滤");

    }

    @Override
    public void destroy() {

    }
}
