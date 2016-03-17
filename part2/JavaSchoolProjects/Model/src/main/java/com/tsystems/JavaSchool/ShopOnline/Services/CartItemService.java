package com.tsystems.JavaSchool.ShopOnline.Services;

import com.tsystems.JavaSchool.ShopOnline.Persistance.Dao.IOrderDAO;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.CartItem;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Order;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Person;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Product;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by asus on 14.03.2016.
 */
public class CartItemService implements ICartItemService {


    Logger logger = Logger.getLogger(CartItemService.class);

    @Autowired
    private IGetCatalogService getCatalogService;

    @Autowired
    private IOrderDAO orderDAO;

    /**
     * Get cart of this user from db and add to it items from another cart. Save result to db.
     * @param user
     * @param oldCart cart, which items we won't to add
     * @return If oldcart is null, returns cart, got from db. </br>
     * N.B.! If there are no cart for this user in db, returns null, even if oldcart is not empty.
     */
    public Map<String, CartItem> mergeCarts(Person user, Map<String, CartItem> oldCart) {

        //Got catalog from db, because someone could add new product
        Map<String,Product> products = getCatalogService.getCatalog();
        Map<String, CartItem> cart = getCart(user);
        if ((cart != null) && (oldCart != null)) {
            logger.info("User had put smth to cart before authorization. " +
                    "And got his previous cart from db. Merging them.");
            for (String id : oldCart.keySet()) {
                logger.info("Found product " + oldCart.get(id).getProduct().getName()
                        + " add it to this user's cart in db");
                cart = addCartItem(products, cart, id, user);
            }
        }
        return cart;
    }

    Map<String, CartItem> getCart(Person user) {

        Order order = orderDAO.getIncompletedOrder(user);
        if ((order != null) && (order.getCartItems() != null))
            return convertToMap(order.getCartItems());
        else
            return null;
    }

    private Map<String,CartItem> convertToMap(List<CartItem> userCartItems) {

            Map<String, CartItem> map = new HashMap<String, CartItem>();
            for (CartItem item : userCartItems) {
                map.put(String.valueOf(item.getProduct().getId()), item);
            }
            return map;
    }

    /**
     * Adds new item to cart or increments counter of existing item. </br>
     * Save item to db if user exists.
     * @param products
     * @param cart existing cart, to which we want put product, or null, if we need new one
     * @param id
     * @param user
     * @return
     */
    public Map<String,CartItem> addCartItem(Map<String, Product> products, Map<String, CartItem> cart, String id, Person user) {

        logger.info("Started");

        if (products == null) {
            logger.error("session expired, gor product from db");
            //load from db if session spoiled
            products =  getCatalogService.getCatalog();
        }

        if (products != null)
            return addToCartAndSave(cart, products, id, user);
        else
            return null;
    }


    private Map<String,CartItem> addToCartAndSave(Map<String, CartItem> cart, Map<String, Product> products, String id, Person user) {
        if (cart == null) {
            logger.info("there is no cart, creating new one");
            cart = new HashMap<String, CartItem>();
        }
        CartItem item = cart.get(id);
        //add to cart if there are no such product
        if (item == null) {
            logger.info("no such product " + products.get(id).getName() + "in cart, adding one");
            item = new CartItem();
            item.setProduct(products.get(id));
        }
        //if there is, just increment its counter
        item.setAmount(item.getAmount() + 1);

        cart = addItemToCartAndDB(cart, id, item, user);

        logger.info("there are " + item.getAmount() +
                " products " + products.get(id).getName() + " in cart now");
        return cart;
    }

    private Map<String,CartItem> addItemToCartAndDB(Map<String, CartItem> cart,
                                                    String id, CartItem item, Person user) {
        cart.put(id, item);
        if (user != null)
            orderDAO.updateCartDB(item,user);
        return cart;
    }


}
