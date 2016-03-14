package com.tsystems.JavaSchool.ShopOnline.Dao;

import java.util.List;

/**
 * Created by asus on 01.03.2016.
 */
public interface IOrderDAO {

    /**
     * gets incompleted order from db or creates new one if not exist
     * @return
     */
    public Order getIncompletedOrder(Person user);

    /**
     * Commit order to db
     * @param order
     */
    void commit(Order order);

    /**
     * Commit item to db
     * @param item
     */
    void commit(CartItem item);

    /**
     * Get cartItems list of order from db
     * @param order
     * @return
     */
    List<CartItem> getCartItems(Order order);
}
