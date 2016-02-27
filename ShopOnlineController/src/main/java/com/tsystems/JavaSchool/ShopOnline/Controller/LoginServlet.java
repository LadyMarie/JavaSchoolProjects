package com.tsystems.JavaSchool.ShopOnline.Controller;

import com.mysql.jdbc.StringUtils;
import com.tsystems.JavaSchool.ShopOnline.Dao.ILoginDAO;
import com.tsystems.JavaSchool.ShopOnline.Dao.LoginDAO;
import com.tsystems.JavaSchool.ShopOnline.Dao.Person;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    String forward;
    private Logger logger = Logger.getLogger(LoginServlet.class);

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info("LoginServlet started");
        String email = req.getParameter("email");
        String pass = req.getParameter("password");

        System.out.print(email + pass);

        HttpSession session = req.getSession();

        forward = "./pages/login.jsp";

        if(!StringUtils.isNullOrEmpty(email) && !StringUtils.isNullOrEmpty(pass)) {
            logger.info("User add data, will process it");
            Person user = getPersonDB(email, DigestUtils.md5Hex(pass));
            if (user == null) {
                logger.error("No such user or wrong credenitials");
                req.setAttribute("LoginError", "Error");
            }
            else {
                logger.info("Logged in successfully!=)) Email: " + email);
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
            logger.info("Something wrong with db. " + ex.getMessage() + " " + ex.getStackTrace().toString());
            forward = "./pages/error.jsp";
            return null;
        }
    }


    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}