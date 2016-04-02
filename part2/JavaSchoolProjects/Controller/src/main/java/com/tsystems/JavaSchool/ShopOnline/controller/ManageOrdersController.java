package com.tsystems.JavaSchool.ShopOnline.controller;

import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Order;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Person;
import com.tsystems.JavaSchool.ShopOnline.Services.IOrderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
@SessionAttributes({"User","orders"})
public class ManageOrdersController implements HandlerExceptionResolver{

	private Logger logger = Logger.getLogger(ManageOrdersController.class);

    @Autowired
    IOrderService orderService;


    @RequestMapping(value = "/manageOrders")
    public String getCatalog(ModelMap model) throws IOException{
        logger.info("Started");
        //protect from unemployed
        Person user = (Person)model.get("User");
        if ((user == null) || (!user.getRole().equals("Employee"))) {
            return "notEmployed";
        } else {
            return getOrders(model);
        }

    }

    private String getOrders(ModelMap model) {
        List<Order> orders = orderService.getOrders();
        //If this is null, there some error while getting orders from db.
        //But list of orders may exist, so we can't display empty page
        if (orders == null) {
            logger.error("Some error occured while geting all orders");
            return "error";
        }
        else {
            model.put("orders", orders);
            return "manageOrders";
        }
    }

    @RequestMapping(value = "/status")
    private String changeStatus(ModelMap model,HttpServletRequest req) {
        Person user = (Person) model.get("User");
        if ((user == null) || (!user.getRole().equals("Employee")))
            return "notEmployed";
        orderService.changeStatus(req.getParameter("id"), req.getParameter("status"));
        return "redirect:manageOrders";
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
