package com.tsystems.JavaSchool.ShopOnline.Controller;

import com.tsystems.JavaSchool.ShopOnline.Dao.CartItem;
import com.tsystems.JavaSchool.ShopOnline.Dao.Person;
import com.tsystems.JavaSchool.ShopOnline.Dao.Product;
import com.tsystems.JavaSchool.ShopOnline.Services.AddCartItemService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by asus on 28.02.2016.
 */
public class CartServlet extends HttpServlet {

    private Logger logger = Logger.getLogger(CartServlet.class);

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        logger.info("Started");
        Map<String, Product> products = (Map<String, Product>) req.getSession().getAttribute("products");
        Map<String, CartItem> cart = (Map<String, CartItem>) req.getSession().getAttribute("cart");
        String id = req.getParameter("id");
        Person user = (Person)req.getSession().getAttribute("User");

        cart = (new AddCartItemService()).addCartItem(products, cart, id, user);
        if (user == null) {
            //todo: try to add product to cart from cookie
        }

        if (cart != null) {
            //write in .jsp that item added
            res.getWriter().write("<p class=\"text-info\"><small>Added: "
                    + cart.get(id).getProduct().getName() + ".</br> Total: " + cart.size() + ".</small></p>");
            res.getWriter().close();
            req.getSession().setAttribute("cart", cart);
            req.getSession().setAttribute("cartKeySet", new ArrayList<>(cart.keySet()));
            req.getSession().setAttribute("cartsize", cart.size());
        }
        else
           req.setAttribute("noCart","true");
           req.getRequestDispatcher("./pages/Index.jsp").forward(req, res);

    }



    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req,res);
    }
}
