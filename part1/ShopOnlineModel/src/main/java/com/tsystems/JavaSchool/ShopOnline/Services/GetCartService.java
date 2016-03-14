package com.tsystems.JavaSchool.ShopOnline.Services;

import com.tsystems.JavaSchool.ShopOnline.Dao.*;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by asus on 01.03.2016.
 */
public class GetCartService implements IGetCartService {

    private Logger logger = Logger.getLogger(GetCartService.class);

    /**
     * Get cart from db for user given
     * @param user
     * @return cart or null, if this user hasn't got a cart
     */
    public Map<String, CartItem> getCart(Person user) {

        Order order = new OrderDAO().getIncompletedOrder(user);
        if (order.getCartItems() != null) {
            List<CartItem> userCartItems = order.getCartItems();
            Map<String, CartItem> map = new HashMap<String, CartItem>();
            for (CartItem item : userCartItems) {
                map.put(String.valueOf(item.getProduct().getId()), item);
            }
            return map;
        }
        return null;
    }
}
