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


@Controller
@SessionAttributes("User")
public class MakeOrderController implements HandlerExceptionResolver {

    Logger logger = Logger.getLogger(LogoutController.class);

    @Autowired
    private IOrderService orderService;


    @RequestMapping(value = "/order")
    public String addLoginForm(ModelMap model) {
        Person user = (Person) model.get("User");
        if (user != null) {
            model.put("Order", orderService.getIncompletedOrder(user));
            return "makeOrder";
        }
        else {
            //protect from anonimys user and client
            return "login";
        }
    }

    @RequestMapping(value = "/addOrder")
    public String doGet(@Valid @ModelAttribute("Order") Order order,
                        BindingResult result, ModelMap model, HttpServletRequest req) {
        logger.info("Started");
        if (result.hasErrors()) {
            logger.info("Trying to make order. " + order.toString() + ". Errors: " + result.toString());
            return "makeOrder";
        } else {
            orderService.makeOrder(order);
            return "redirect:Main";
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
