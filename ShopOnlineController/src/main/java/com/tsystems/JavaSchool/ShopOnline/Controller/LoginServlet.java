package com.tsystems.JavaSchool.ShopOnline.Controller;

import com.mysql.jdbc.StringUtils;
import com.tsystems.JavaSchool.ShopOnline.Dao.*;
import com.tsystems.JavaSchool.ShopOnline.Services.AddCartItemService;
import com.tsystems.JavaSchool.ShopOnline.Services.GetCartService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class LoginServlet extends HttpServlet {

    private Logger logger = Logger.getLogger(LoginServlet.class);

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info("LoginServlet started");
        String email = req.getParameter("email");
        String pass = req.getParameter("password");

        System.out.print(email + pass);

        HttpSession session = req.getSession();

        if(!StringUtils.isNullOrEmpty(email) && !StringUtils.isNullOrEmpty(pass)) {
            logger.info("User add data, will process it");
            Person user = getPersonDB(email, DigestUtils.md5Hex(pass), req);
            if (user == null) {
                logger.error("No such user or wrong credenitials");
                req.setAttribute("LoginError", "Error");
                req.getRequestDispatcher("./pages/login.jsp").forward(req, resp);
            }
            else {
                logger.info("Logged in successfully!=)) Email: " + email);
                session.setAttribute("User", user);
                tryGetCartDB(user, req);
                req.getRequestDispatcher("/catalog").forward(req, resp);
            }
        }
        else
            req.getRequestDispatcher("./pages/login.jsp").forward(req, resp);

    }

    private Person getPersonDB(String email, String pass, HttpServletRequest req) {
        try {
            return new LoginDAO().getPersonDB(email, pass);
        }
        catch (Exception ex) {
            logger.info("Something wrong with db. " + ex.getMessage() + " " + ex.getStackTrace().toString());
            return null;
        }
    }

    private void tryGetCartDB(Person user, HttpServletRequest req) {
            if (req.getSession().getAttribute("User") != null) {
                logger.info("User " + ((Person)req.getSession().getAttribute("User")).getName()
                        + " has logged in. Restoring his cart from db.");
                Map<String, CartItem> oldCart = (Map<String, CartItem>) req.getSession().getAttribute("cart");
                Map<String, Product> products = (Map<String, Product>) req.getSession().getAttribute("products");

                Map<String, CartItem> cart = (new AddCartItemService()).mergeCarts(user,oldCart,products);

                if (cart != null) {
                    req.getSession().setAttribute("cart", cart);
                    req.getSession().setAttribute("cartKeySet", new ArrayList<>(cart.keySet()));
                    req.getSession().setAttribute("cartsize", cart.size());
                }
            }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}