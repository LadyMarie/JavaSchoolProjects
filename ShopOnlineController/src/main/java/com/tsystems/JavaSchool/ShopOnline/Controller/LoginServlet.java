package com.tsystems.JavaSchool.ShopOnline.Controller;

import com.mysql.jdbc.StringUtils;
import com.tsystems.JavaSchool.ShopOnline.Dao.ILoginDAO;
import com.tsystems.JavaSchool.ShopOnline.Dao.LoginDAO;
import com.tsystems.JavaSchool.ShopOnline.Dao.Person;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    String forward;

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String pass = req.getParameter("password");

        System.out.print(email + pass);

        HttpSession session = req.getSession();

        forward = "./pages/login.jsp";

        if(!StringUtils.isNullOrEmpty(email) && !StringUtils.isNullOrEmpty(pass)) {
            Person user = getPersonDB(email, DigestUtils.md5Hex(pass));
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

    private Person getPersonDB(String email, String pass) {
        try {
            return new LoginDAO().getPersonDB(email, pass);
        }
        catch (Exception ex) {
            //Todo: Write stacktrace to log
            forward = "./pages/error.jsp";
            return null;
        }
    }


    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}