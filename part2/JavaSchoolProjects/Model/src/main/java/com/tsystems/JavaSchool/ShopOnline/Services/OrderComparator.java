package com.tsystems.JavaSchool.ShopOnline.Services;

import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Order;
import org.apache.log4j.Logger;

import java.util.Comparator;

/**
 * Created by asus on 31.03.2016.
 */
public class OrderComparator implements Comparator<Order> {

    Logger logger = Logger.getLogger(OrderComparator.class);

    public int compare(Order order, Order t1) {
        try {
            int i =  order.getUser().getEmail().compareTo(((Order)t1).getUser().getEmail());
            return i;
        }
        catch (NullPointerException ex) {
            logger.error("Order hasn't got a user " + order.toString());
            //if there is order without user, there are some mistake in business logic
            return 0;
        }
    }
}
