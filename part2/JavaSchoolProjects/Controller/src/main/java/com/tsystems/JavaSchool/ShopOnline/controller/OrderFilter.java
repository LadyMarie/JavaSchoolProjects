package com.tsystems.JavaSchool.ShopOnline.controller;



import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Person;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;
import java.io.IOException;

public class OrderFilter implements Filter
{
    public void init(FilterConfig filterConfig) throws ServletException
    {

    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException
    {
        String redirect = ((HttpServletRequest)req).getContextPath() + "/order";
        ((HttpServletResponse)res).sendRedirect(redirect);
    }

    public void destroy()
    {

    }
}
