package com.tsystems.JavaSchool.ShopOnline.Persistance.Dao;

import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.CartItem;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Order;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Person;

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
}
