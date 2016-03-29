package com.tsystems.JavaSchool.ShopOnline.Services;

import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Order;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Person;

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
}
