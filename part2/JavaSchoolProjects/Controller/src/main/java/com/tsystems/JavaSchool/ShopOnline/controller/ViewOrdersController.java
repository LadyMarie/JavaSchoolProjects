package com.tsystems.JavaSchool.ShopOnline.controller;

import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.CartItem;
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
import java.util.Map;


@Controller
@SessionAttributes({"User","orders"})
//"orders","orderCart","orderCartKeySet","orderCartSize"
public class ViewOrdersController implements HandlerExceptionResolver{

	private Logger logger = Logger.getLogger(ViewOrdersController.class);

    @Autowired
    IOrderService orderService;


    @RequestMapping(value = "/viewOrders")
    public String getCatalog(ModelMap model) throws IOException{

       logger.info("Started");
        Person user = (Person)model.get("User");
        if (user == null)
            //protect from anonimys user
            return "redirect:login";
        else {
            return getOrders(model, user);

        }
	}

    private String getOrders(ModelMap model, Person user) {
        ArrayList<Order> orders = orderService.getUserOrders(user);
        //If this is null, there some error while getting orders from db.
        //But list of orders may exist, so we can't display empty page
        if (orders == null) {
            logger.error("Some error occured while geting " +
                    "orderList from db for user " + user.toString());
            return "error";
        }
        else {
            model.put("orders", orders);
            return "viewOrders";
        }
    }

    @RequestMapping(value = "/repeat")
    public String repeat(ModelMap model, HttpServletRequest req) {
        Person user = (Person)model.get("User");
        if (user == null)
            //protect from anonimys user
            return "redirect:login";
        else
            return repeatOrder(model,req);
    }

    private String repeatOrder(ModelMap model, HttpServletRequest req) {
        List<Order> orders = (ArrayList<Order>) model.get("orders");
        Order order = orderService.repeatOrder(orders, req.getParameter("id"));
        if (order != null) {
            //make able to update page and view new order
            return "redirect:viewOrders";
        } else {
            return "error";
        }
    }

    @RequestMapping(value = "/viewCart")
    public String viewCart(HttpServletRequest req, ModelMap model) {
        Person user = (Person)model.get("User");
        if (user == null)
            //protect from anonimys user
            return "redirect:login";
        else
            return viewProducts(req,model);
    }

    private String viewProducts(HttpServletRequest req, ModelMap model) {
        ArrayList<Order> orders = (ArrayList<Order>)model.get("orders");
        Map<String,CartItem> orderCart = orderService.getCart(req.getParameter("id"),orders);
        model.put("orderCart",orderCart);
        model.put("orderCartKeySet", new ArrayList<String>(orderCart.keySet()));
        model.put("orderCartSize", orderCart.size());
        return "orderCart";
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
