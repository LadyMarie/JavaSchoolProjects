package com.tsystems.JavaSchool.ShopOnline.Services;

import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.CartItem;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Person;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Product;

import java.util.List;
import java.util.Map;

/**
 * Created by asus on 14.03.2016.
 */
public interface ICartItemService {

    /**
     * Get cart of this user from db and add to it items from another cart. Save result to db.
     * @param user
     * @param oldCart cart, which items we won't to add
     * @return If oldcart is null, returns cart, got from db. </br>
     * N.B.! If there are no cart for this user in db, returns null, even if oldcart is not empty.
     */
    Map<String, CartItem> mergeCarts(Person user, Map<String, CartItem> oldCart);


    /**
     * Adds new item to cart or increments counter of existing item. </br>
     * Save item to db if user exists.
     * @param products
     * @param cart existing cart, to which we want put product, or null, if we need new one
     * @param id
     * @param user
     * @return
     */
    Map<String,CartItem> addCartItem(Map<String, Product> products, Map<String, CartItem> cart, String id, Person user);

    /**
     * converts arrayList to map for better view representation
     * @param userCartItems
     * @return
     */
    public Map<String,CartItem> convertToMap(List<CartItem> userCartItems);
}
