package com.filter;

import javax.servlet.*;
import java.io.IOException;

public class CharsetEncodingFilter implements Filter {

    public CharsetEncodingFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化init...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("过滤器doFilter...");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html,charset=urf-8");
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("过滤器destroy...");
    }
}
