package com.tsystems.JavaSchool.ShopOnline.Services;

import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.CartItem;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Order;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by asus on 21.02.2016.
 */
public interface IOrderService {

    /**
     * set order to state "completed" and save its info to db
     */
   public void makeOrder(Order order);

    /**
     * gets incompleted order from db or creates new one if not exist
     * @return
     */
    Order getIncompletedOrder(Person user);

    /**
     * get list of orders for this user
     */
    ArrayList<Order> getUserOrders(Person user);

    /**
     * get all orders and sort it by alphabet user email
     */
    ArrayList<Order> getOrders();

    /**
     * change status of order with id given
     */
    void changeStatus(String id, String status);

    /**
     * Repeat order with id given
     * @param orders list of orders, previously got from db,
     *               because, if we saved this list in controller,
     *               we don't need to call db one more time
     * @param strId
     * @return
     */
    Order repeatOrder(List<Order> orders, String strId);

    /**
     * Get cart for order given
     * @param orderId
     * @param orders list of orders, previously got from db,
     *               because, if we saved this list in controller,
     *               we don't need to call db one more time
      * @return
     */
    Map<String,CartItem> getCart(String orderId, ArrayList<Order> orders);
}
