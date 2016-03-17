package com.tsystems.JavaSchool.ShopOnline.controller;

import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.CartItem;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by asus on 21.02.2016.
 */
@Controller
public class LogoutController {

    @RequestMapping(value = "/logout")
    String Login(HttpServletRequest req) throws IOException {
        //keep catalog while invalidating
        Map<String,Product> products = (Map<String,Product>)req.getSession().getAttribute("products");
        req.getSession().invalidate();
        req.getSession().setAttribute("products", products);
        req.getSession().setAttribute("productsKeySet", new ArrayList<String>(products.keySet()));
        return "Index";
    }

}
