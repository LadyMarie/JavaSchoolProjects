package com.tsystems.JavaSchool.ShopOnline.Controller;

import com.mysql.jdbc.StringUtils;
import com.tsystems.JavaSchool.ShopOnline.Dao.ILoginDAO;
import com.tsystems.JavaSchool.ShopOnline.Dao.LoginDAO;
import com.tsystems.JavaSchool.ShopOnline.Dao.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String pass = req.getParameter("password");

        System.out.print(email + pass);

        HttpSession session = req.getSession();

        String forward = "./pages/login.jsp";
        if(!StringUtils.isNullOrEmpty(email) && !StringUtils.isNullOrEmpty(pass)) {
            Person user = new LoginDAO().getPersonDB(email, pass);
            if (user == null) {
                req.setAttribute("LoginError", "Error");
            }
            else {
                session.setAttribute("User", user);
                forward = "./pages/Index.jsp";
            }
        }
        req.getRequestDispatcher(forward).forward(req, resp);

    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}