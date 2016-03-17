package com.tsystems.JavaSchool.ShopOnline.controller;

import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.CartItem;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Person;
import com.tsystems.JavaSchool.ShopOnline.Services.ICartItemService;
import com.tsystems.JavaSchool.ShopOnline.Services.ILoginService;
import com.tsystems.JavaSchool.ShopOnline.controller.validator.PersonValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by asus on 17.03.2016.
 */
@Controller
public class LoginController {

    Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    ILoginService loginService;

    @Autowired
    private ICartItemService cartItemService;

    @Autowired
    PersonValidator validator;

    @RequestMapping(value="login", method= RequestMethod.GET)
    public String addLoginForm(ModelMap model) {
        model.addAttribute("User", new Person());
        return "login";
    }

    @RequestMapping(value="/loginPerson")
    public String signup(@Valid @ModelAttribute("User") Person user, BindingResult result, HttpServletRequest req) {

        validator.validate(user, result);
        if (result.hasErrors()) {
            logger.info("User " + user.getName() + " tried to login. Errors " + result.toString());
            return "login";
        }
        else {
            logger.info("User " + user.getName() + " logged on successfully.");
            Person userFromDb = validator.getUser();
            req.getSession().setAttribute("User", userFromDb);
            req.getSession().setAttribute("name", userFromDb.getName());
            tryGetCartDB(userFromDb, req);
            return "Index";
        }
    }

    private void tryGetCartDB(Person user, HttpServletRequest req) {
            logger.info("Restoring his cart from db.");
            Map<String, CartItem> oldCart = (Map<String, CartItem>) req.getSession().getAttribute("cart");

            Map<String, CartItem> cart = cartItemService.mergeCarts(user, oldCart);

            if (cart != null) {
                req.getSession().setAttribute("cart", cart);
                req.getSession().setAttribute("cartKeySet", new ArrayList<String>(cart.keySet()));
                req.getSession().setAttribute("cartsize", cart.size());
            }
    }
}
