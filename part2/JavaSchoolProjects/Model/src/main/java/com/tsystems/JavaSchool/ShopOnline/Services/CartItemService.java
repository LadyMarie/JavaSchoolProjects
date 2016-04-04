package com.tsystems.JavaSchool.ShopOnline.Services;

import com.tsystems.JavaSchool.ShopOnline.Persistance.Dao.IOrderDAO;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Dao.IProductDAO;
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
    private IProductService getCatalogService;

    @Autowired
    private IOrderDAO orderDAO;

    @Autowired
    private IProductDAO productDAO;

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

    /**
     * converts arrayList to map for better view representation
     * @param userCartItems
     * @return
     */
    public Map<String,CartItem> convertToMap(List<CartItem> userCartItems) {

            Map<String, CartItem> map = new HashMap<String, CartItem>();
            if (userCartItems != null) {
                for (CartItem item : userCartItems) {
                    map.put(String.valueOf(item.getProduct().getId()), item);
                }
            }
            else {
                logger.error("Cart item list is null");
            }
            return map;
    }

    /**
     * Adds new item to cart or increments counter of existing item. </br>
     * Save item to db if user exists. Decrease amount of corresponding product.
     * If product has been run out, add nothing to cart.
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
            if (products == null) {
                logger.error("can't got catalog from db");
                return null;
            }
        }
        return addToCartAndSave(cart, products, id, user);
    }



    private Map<String,CartItem> addToCartAndSave(Map<String, CartItem> cart, Map<String, Product> products, String id, Person user) {
        if (cart == null) {
            logger.info("there is no cart, creating new one");
            cart = new HashMap<String, CartItem>();
        }
        Product product = products.get(id);
        CartItem item = cart.get(id);
        //add to cart if there are no such product
        if (item == null) {
            logger.info("no such product " + products.get(id).getName() + "in cart, adding one");
            item = new CartItem();
            item.setProduct(product);
        }
        //decrement count of this product in shop
        if (bookProductInCatalog(product)) {
            //if there is, just increment its counter
            item.setAmount(item.getAmount() + 1);
            cart = addItemToCartAndDB(cart, id, item, user);
        }

        logger.info("there are " + item.getAmount() +
                " products " + products.get(id).getName() + " in cart now");
        return cart;
    }


    /**
     * We have to 'book' product in the shop to disallow different users add to carts
     * more items of this product, then it is in the stock.
     * When booking, amount of this product in catalog is decreased, and then
     * new value is saved to db.
     * @param product
     * @return
     * true - if there is enough product in the stock
     * and item may be added to cart,
     * false - if product has ended in the stock
     */
    private boolean bookProductInCatalog(Product product) {
        logger.info("Book product " + product.toString());
        //we can be sure that it will be parsed successfully,
        //because of validation of amount field
        int prodAmount = Integer.parseInt(product.getAmount());
        if (prodAmount > 0) {
            logger.info("Now its amount is" + (prodAmount - 1));
            product.setAmount(String.valueOf(prodAmount - 1));
            productDAO.saveProduct(product);
            return true;
        }
        else
            return false;
    }

    private Map<String,CartItem> addItemToCartAndDB(Map<String, CartItem> cart,
                                                    String id, CartItem item, Person user) {
        cart.put(id, item);
        if (user != null)
            orderDAO.updateCartDB(item,user);
        return cart;
    }


}
