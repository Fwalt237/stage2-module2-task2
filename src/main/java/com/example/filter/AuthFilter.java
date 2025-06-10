package com.example.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebFilter
public class AuthFilter implements Filter  {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

@Override
public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                     FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpSession session = request.getSession(false); // Don't create new session

    if (session == null || session.getAttribute("user") == null) {
        request.getRequestDispatcher("/login.jsp").forward(request, servletResponse);
        return;
    }
    filterChain.doFilter(servletRequest, servletResponse);
}

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
    //write your code here!
}