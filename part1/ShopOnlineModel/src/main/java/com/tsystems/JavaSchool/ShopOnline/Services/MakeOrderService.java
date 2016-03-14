package com.tsystems.JavaSchool.ShopOnline.Services;

import com.tsystems.JavaSchool.ShopOnline.Dao.Order;
import com.tsystems.JavaSchool.ShopOnline.Dao.OrderDAO;
import com.tsystems.JavaSchool.ShopOnline.Dao.Person;

/**
 * Created by asus on 04.03.2016.
 */
public class MakeOrderService implements  IMakeOrderService {


    /**
     * set order to state "complited"
     * @param user
     * @param pay
     * @param delivery
     */
    public void makeOrder(Person user, String pay, String delivery) {

        OrderDAO orderDAO = new OrderDAO();
        Order order = orderDAO.getIncompletedOrder(user);
        order.setPayMethod(pay);
        order.setDeliveryMethod(pay);
        order.setCompleted(true);
        orderDAO.commit(order);

    }
}
