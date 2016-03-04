package com.tsystems.JavaSchool.ShopOnline.Services;

import com.tsystems.JavaSchool.ShopOnline.Dao.CartItem;
import com.tsystems.JavaSchool.ShopOnline.Dao.Person;
import com.tsystems.JavaSchool.ShopOnline.Dao.Product;

import java.util.Map;

/**
 * Created by asus on 21.02.2016.
 */
public interface IAddCartItemService {

    /**
     * Get cart of this user from db and add to it items from another cart. Save result to db.
     * @param user
     * @param oldCart cart, which items we won't to add
     * @param products
     * @return
     */
    Map<String, CartItem> mergeCarts(Person user, Map<String, CartItem> oldCart,
                                     Map<String, Product> products);

    /**
     * Adds new item to cart or increments counter of existing item. </br>
     * Save item to db if user exists.
     * @param products
     * @param cart existing cart or null
     * @param id
     * @param user
     * @return
     */
    Map<String,CartItem> addCartItem(Map<String, Product> products,
                                     Map<String, CartItem> cart, String id, Person user);
}
