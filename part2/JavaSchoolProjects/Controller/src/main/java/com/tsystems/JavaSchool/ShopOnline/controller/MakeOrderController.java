package com.tsystems.JavaSchool.ShopOnline.controller;


import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Order;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Person;
import com.tsystems.JavaSchool.ShopOnline.Services.IOrderService;
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
import java.util.HashMap;


@Controller
@SessionAttributes({"User","Order","cart","cartKeySet","cartsize"})
public class MakeOrderController implements HandlerExceptionResolver {

    Logger logger = Logger.getLogger(LogoutController.class);

    @Autowired
    private IOrderService orderService;


    @RequestMapping(value = "/order")
    public String addLoginForm(ModelMap model) {
        Person user = (Person) model.get("User");
        if (user != null) {
            return initOrder(user, model);
        }
        else {
            //protect from anonimys user
            return "redirect:login";
        }
    }

    //check if cart is empty - nothing to order, then ask to add smth
    private String initOrder(Person user, ModelMap model) {
        Order order = orderService.getIncompletedOrder(user);
        if (order.getCartItems() == null)
            return "emptyCart";
        else {
            model.put("Order", order);
            return "makeOrder";
        }
    }


    //Init order if someone types in browser '/makeOrder', accessing form directly
    @ModelAttribute("Order")
    public void initPerson(ModelMap model) {
        Person user = (Person) model.get("User");
        Order order = orderService.getIncompletedOrder(user);
        if (order.getCartItems() != null)
            model.put("Order", order);
    }

    @RequestMapping(value = "/addOrder")
    public String makeOrder(@Valid @ModelAttribute("Order") Order order,
                        BindingResult result, ModelMap model, HttpServletRequest req) {
        logger.info("Started");
        if (result.hasErrors()) {
            logger.info("Trying to make order. " + order.toString() + ". Errors: " + result.toString());
            return "makeOrder";
        } else {
            orderService.makeOrder(order);
            cleanCart(model);
            return "redirect:Main";
        }
    }

    //reset all cart items, because they've just been ordered,
    //we don't need them in next order
    private void cleanCart(ModelMap model) {
        model.put("cart", new HashMap<>());
        model.put("cartKeySet", new ArrayList<String>());
        model.put("cartsize", 0);
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
