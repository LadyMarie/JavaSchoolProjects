package com.tsystems.JavaSchool.ShopOnline.Controller;

import com.tsystems.JavaSchool.ShopOnline.Dao.Person;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderFilter implements Filter
{
    public void init(FilterConfig filterConfig) throws ServletException
    {

    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException
    {
        HttpServletRequest httpReq = (HttpServletRequest)req;
        Person user = (Person) httpReq.getSession().getAttribute("User");
        if (user != null) {
            filterChain.doFilter(httpReq, (HttpServletResponse)res);
        } else {
            String forward = "./pages/login.jsp";
            req.getRequestDispatcher(forward).forward(req, res);
        }
    }

    public void destroy()
    {

    }
}
