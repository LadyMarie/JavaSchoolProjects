package com.tsystems.JavaSchool.ShopOnline.controller;

import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.CartItem;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Person;
import com.tsystems.JavaSchool.ShopOnline.Services.ICartItemService;
import com.tsystems.JavaSchool.ShopOnline.Services.IPersonService;
import com.tsystems.JavaSchool.ShopOnline.controller.validator.PersonValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by asus on 17.03.2016.
 */
@Controller
@SessionAttributes({"User","cart","cartKeySet","cartsize"})
public class LoginController implements HandlerExceptionResolver{

    Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    IPersonService personService;

    @Autowired
    private ICartItemService cartItemService;

    @Autowired
    PersonValidator validator;

    @RequestMapping(value="login", method= RequestMethod.GET)
    public String addLoginForm(ModelMap model) {
        model.put("User", new Person());
        return "loginUser";
    }

    //Init person if someone types in browser '/loginPerson', accessing form directly
    @ModelAttribute("User")
    public void initPerson(ModelMap model) {
        if (model.get("User") == null )
            model.put("User", new Person());
    }

    @RequestMapping(value="/loginPerson")
    public String login(@Valid @ModelAttribute("User") Person user, BindingResult result, ModelMap model) {

            validator.validate(user, result);
            if (result.hasErrors()) {
                logger.info("User " + user.getName() + " tried to login. Errors " + result.toString());
                return "loginUser";
            } else {
                logger.info("User " + user.getName() + " logged on successfully.");
                user = validator.getUser();
                model.put("User", user);
                tryGetCartDB(user, model);
                return "redirect:Main";
            }

    }


    private void tryGetCartDB(Person user, ModelMap model) {

           logger.info("Restoring his cart from db.");
           Map<String, CartItem> oldCart = (Map<String, CartItem>)model.get("cart");

           Map<String, CartItem> cart = cartItemService.mergeCarts(user, oldCart);

            if (cart != null) {
                model.put("cart",cart);
                model.put("cartKeySet",new ArrayList<String>(cart.keySet()));
                model.put("cartsize",cart.size());

            }
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
