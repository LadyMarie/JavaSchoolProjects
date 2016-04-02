package com.tsystems.JavaSchool.ShopOnline.Persistance.Dao;

import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.CartItem;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Order;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 14.03.2016.
 */
public interface IOrderDAO {

    /**
     * gets incompleted order from db or creates new one if not exist
     * @return
     */
    Order getIncompletedOrder(Person user);

    /**
     * Add item to incompleted order of this user
     * @param item
     * @param user
     */
    void updateCartDB(CartItem item, Person user);

    /**
     * save order to db
     * @param order
     */
    void saveOrder(Order order);

    /**
     * get list of orders for this user
     */
    ArrayList<Order> getUserOrders(Person user);

    /**
     * get all orders and sort it by alphabet user email
     */
    ArrayList<Order> getOrders();

    /**
     * get order with id given
     * @param id
     * @return
     */
    Order getOrder(Long id);
}
