package com.tsystems.JavaSchool.ShopOnline.controller;

import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Person;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Product;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.CartItem;
import com.tsystems.JavaSchool.ShopOnline.Services.ICartItemService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by asus on 28.02.2016.
 */
@Controller
@SessionAttributes({"products","cart","User","cartKeySet","cartsize"})
public class CartController  implements HandlerExceptionResolver {

    private Logger logger = Logger.getLogger(CartController.class);

    @Autowired
    ICartItemService cartItemService;


    @RequestMapping(value = "/cart")
    public String addCartItem(HttpServletRequest req, HttpServletResponse res, ModelMap model) throws IOException {

        logger.info("Started");
        Map<String, Product> products = (Map<String, Product>) model.get("products");
        Map<String, CartItem> cart = (Map<String, CartItem>) model.get("cart");
        String id = req.getParameter("id");
        Person user = (Person)model.get("User");

        cart = cartItemService.addCartItem(products, cart, id, user);
        if (user == null) {
            //todo: try to add product to cart from cookie
        }

        if (cart != null) {
            //write in .jsp that item added
            res.getWriter().write("<p class=\"text-info\"><small>Added: "
                    + cart.get(id).getProduct().getName() + ".</br> Total: " + cart.size() + ".</small></p>");
            res.getWriter().close();
            model.put("cart", cart);
            model.put("cartKeySet", new ArrayList<String>(cart.keySet()));
            model.put("cartsize", cart.size());
        }
        else
           model.put("noCart","true");
           return "Index";

    }

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse, Object o, Exception e) {
            logger.error("Error", e);
            return new ModelAndView("error");
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest req, Exception e) {
        logger.error("Error", e);
        return new ModelAndView("error");
    }

}
