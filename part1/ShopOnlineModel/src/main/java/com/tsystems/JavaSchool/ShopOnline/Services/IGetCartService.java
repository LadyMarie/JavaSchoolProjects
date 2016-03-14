package com.tsystems.JavaSchool.ShopOnline.Services;

import com.tsystems.JavaSchool.ShopOnline.Dao.CartItem;
import com.tsystems.JavaSchool.ShopOnline.Dao.Person;

import java.util.Map;

/**
 * Created by asus on 01.03.2016.
 */
public interface IGetCartService {

    /**
     * Get cart from db for user given
     * @param user
     * @return cart or null, if this user hasn't got a cart
     */
    Map<String, CartItem> getCart(Person user);
}
