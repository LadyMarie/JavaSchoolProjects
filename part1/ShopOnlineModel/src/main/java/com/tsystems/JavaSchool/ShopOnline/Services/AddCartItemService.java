package com.tsystems.JavaSchool.ShopOnline.Services;

import com.tsystems.JavaSchool.ShopOnline.Dao.*;
import org.apache.log4j.Logger;

;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by asus on 21.02.2016.
 */
public class AddCartItemService implements IAddCartItemService {

    private Logger logger = Logger.getLogger(AddCartItemService.class);

    /**
     * Get cart of this user from db and add to it items from another cart. Save result to db.
     * @param user
     * @param oldCart cart, which items we won't to add
     * @param products
     * @return If oldcart is null, returns cart, got from db. </br>
     * N.B.! If there are no cart for this user in db, returns null, even if oldcart is not empty.
     */
    public Map<String, CartItem> mergeCarts(Person user, Map<String, CartItem> oldCart,
                                             Map<String, Product> products) {
        Map<String, CartItem> cart = new GetCartService().getCart(user);
        if ((cart != null) && (oldCart != null)) {
            logger.info("User had put smth to cart before authorization. " +
                    "And got his previous cart from db. Merging them.");
            for (String id : oldCart.keySet()) {
                logger.info("Found product " + oldCart.get(id).getProduct().getName()
                        + " add it to this user's cart in db");
                cart = (new AddCartItemService()).addCartItem(products, cart, id, user);
            }
        }
        return cart;
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
            products = tryLoadFromDb();
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
            updateCartDB(item, user);
        return cart;
    }

    private Map<String,Product> tryLoadFromDb() {
        try {
            return new AddProductDAO().getCatalog();
        }
        catch (Exception ex) {
            logger.error("Can't get catalog from db. " + ex.getMessage()
                    + " " + ex.getStackTrace());
            return null;
        }
    }

    private void updateCartDB(CartItem item, Person user) {
        logger.info("Saving item to db for user " + user + " Item: " + item.getProduct().getName());
        IOrderDAO orderDAO = new OrderDAO();
        Order order = orderDAO.getIncompletedOrder(user);
        order = fillOrder(order, user);
        if (item.getOrders() == null) {
            item.setOrders(order);
        }
        List<CartItem> ci = order.getCartItems();
        ci.add(item);
        order.setCartItems(ci);
        orderDAO.commit(order);
        orderDAO.commit(item);
    }

    private Order fillOrder(Order order, Person user) {
        if (order.getUser() == null)
            order.setUser(user);
        if (order.getCompleted() != false)
            order.setCompleted(false);
        if (order.getCartItems() == null)
            order.setCartItems(new ArrayList<CartItem>());
        return order;
    }
}
