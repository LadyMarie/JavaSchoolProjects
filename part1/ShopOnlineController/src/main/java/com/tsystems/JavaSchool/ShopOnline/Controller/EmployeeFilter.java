package com.tsystems.JavaSchool.ShopOnline.Controller;

import com.tsystems.JavaSchool.ShopOnline.Dao.Person;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by asus on 25.02.2016.
 */
public class EmployeeFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException
    {
        HttpServletRequest httpReq = (HttpServletRequest)req;
        Person user = (Person) httpReq.getSession().getAttribute("User");
        if ((user != null) && (user.getRole().getName()).equals("Employee")) {
            filterChain.doFilter(httpReq, (HttpServletResponse)res);
        } else {
            String forward = "./pages/notEmployed.jsp";
            req.getRequestDispatcher(forward).forward(req, res);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
