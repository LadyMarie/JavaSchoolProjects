package com.tsystems.JavaSchool.ShopOnline.controller;

import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.CartItem;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

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
@SessionAttributes({"products","productsKeySet"})
public class LogoutController {

    @RequestMapping(value = "/logout")
    String Login(HttpServletRequest req, ModelMap model) throws IOException {
        //keep catalog while invalidating
        Map<String,Product> products = (Map<String,Product>)model.get("products");
        req.getSession().invalidate();
        model.put("products", products);
        model.put("productsKeySet", new ArrayList<String>(products.keySet()));
        return "Index";
    }

}
