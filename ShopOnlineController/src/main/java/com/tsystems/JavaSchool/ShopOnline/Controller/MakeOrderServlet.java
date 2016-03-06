package com.tsystems.JavaSchool.ShopOnline.Controller;

import com.mysql.jdbc.StringUtils;
import com.tsystems.JavaSchool.ShopOnline.Dao.ILoginDAO;
import com.tsystems.JavaSchool.ShopOnline.Dao.LoginDAO;
import com.tsystems.JavaSchool.ShopOnline.Dao.Person;
import com.tsystems.JavaSchool.ShopOnline.Services.MakeOrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MakeOrderServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String forward = "./pages/makeOrder.jsp";

        Person user = (Person)req.getSession().getAttribute("User");
        String delivery = req.getParameter("delivery");
        String pay = req.getParameter("pay");
        if (!StringUtils.isNullOrEmpty(pay) && !StringUtils.isNullOrEmpty(delivery)) {
            new MakeOrderService().makeOrder(user, pay, delivery);
            forward = "/catalog";
        }
        req.getRequestDispatcher(forward).forward(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
