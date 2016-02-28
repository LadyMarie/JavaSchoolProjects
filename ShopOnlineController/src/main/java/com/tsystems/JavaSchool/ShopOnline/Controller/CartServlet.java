package com.tsystems.JavaSchool.ShopOnline.Controller;

import com.tsystems.JavaSchool.ShopOnline.Dao.AddProductDAO;
import com.tsystems.JavaSchool.ShopOnline.Dao.CartItem;
import com.tsystems.JavaSchool.ShopOnline.Dao.Product;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by asus on 28.02.2016.
 */
public class CartServlet extends HttpServlet {

    private Logger logger = Logger.getLogger(CartServlet.class);

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //todo: handle exceptions
        Map<String, Product> products = (Map<String, Product>) req.getSession().getAttribute("products");

        String id = req.getParameter("id");

        Map<String, CartItem> cart = (Map<String, CartItem>) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
        }
        CartItem item = cart.get(id);
        if (item == null) {
            item = new CartItem();
            item.setProduct(products.get(id));
        }
        item.setAmount(item.getAmount() + 1);
        cart.put(id, item);

        req.getSession().setAttribute("cart", cart);

        res.getWriter().write("Added item: " + products.get(id).getName() + ". Total items: " + cart.size());
        res.getWriter().close();
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req,res);
    }
}
