package com.tsystems.JavaSchool.ShopOnline.Services;

import com.tsystems.JavaSchool.ShopOnline.Persistance.Dao.IOrderDAO;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Dao.OrderDAO;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Order;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Person;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by asus on 04.03.2016.
 */
public class OrderService implements  IOrderService {


    @Autowired
    private IOrderDAO orderDAO;

    /**
     * set order to state "completed" and save its info to db
     */
    public void makeOrder(Order order) {
        order.setStatus("new");
        order.setCompleted(true);
        orderDAO.saveOrder(order);
    }

    /**
     * gets incompleted order from db or creates new one if not exist
     * @return
     */
    public Order getIncompletedOrder(Person user) {
        return orderDAO.getIncompletedOrder(user);
    }
}
