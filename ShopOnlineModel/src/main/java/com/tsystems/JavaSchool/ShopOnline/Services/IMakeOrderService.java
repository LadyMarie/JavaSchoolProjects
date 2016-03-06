package com.tsystems.JavaSchool.ShopOnline.Services;

import com.tsystems.JavaSchool.ShopOnline.Dao.CartItem;
import com.tsystems.JavaSchool.ShopOnline.Dao.Person;
import com.tsystems.JavaSchool.ShopOnline.Dao.Product;

import java.util.Map;

/**
 * Created by asus on 21.02.2016.
 */
public interface IMakeOrderService {

    /**
     * set order to state "complited"
     * @param user
     * @param pay
     * @param delivery
     */
   public void makeOrder(Person user, String pay, String delivery);

}
