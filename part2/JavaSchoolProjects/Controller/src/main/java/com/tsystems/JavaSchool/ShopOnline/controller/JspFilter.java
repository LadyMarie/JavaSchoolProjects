package com.tsystems.JavaSchool.ShopOnline.controller;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by asus on 30.03.2016.
 * Forbid direct access to jsp pages, containing form, because in that
 * case @ModelAttribute can't be initialized and exception will be throwen
 */
public class JspFilter implements Filter {


    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        String forward = "/error";
        req.getRequestDispatcher(forward).forward(req, resp);
    }

    @Override
    public void destroy() {

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
