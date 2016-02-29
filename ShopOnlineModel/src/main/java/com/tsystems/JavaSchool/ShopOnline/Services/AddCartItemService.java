package com.tsystems.JavaSchool.ShopOnline.Services;

import com.tsystems.JavaSchool.ShopOnline.Dao.AddProductDAO;
import com.tsystems.JavaSchool.ShopOnline.Dao.CartItem;
import com.tsystems.JavaSchool.ShopOnline.Dao.Product;
import org.apache.commons.collections.functors.ExceptionClosure;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by asus on 21.02.2016.
 */
public class AddCartItemService implements IAddCartItemService {

    private Logger logger = Logger.getLogger(AddCartItemService.class);

    public Map<String,CartItem> addCartItem(Map<String, Product> products, Map<String, CartItem> cart, String id) {

        logger.info("Started");

        if (products == null)
            logger.error("session expired, gor product from db");
            //load from db if session spoiled
            products = tryLoadFromDb(products);

        if (products != null)
            return addToCart(cart, products, id);
        else
            return null;
    }

    private Map<String,CartItem> addToCart(Map<String, CartItem> cart, Map<String, Product> products, String id) {
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
        cart.put(id, item);
        logger.info("there are " + item.getAmount() +
                " products " + products.get(id).getName() + " in cart now");
        return cart;
    }

    private Map<String,Product> tryLoadFromDb(Map<String, Product> products) {
        try {
            return new AddProductDAO().getCatalog();
        }
        catch (Exception ex) {
            logger.error("Can't get catalog from db. " + ex.getMessage()
                    + " " + ex.getStackTrace());
            return null;
        }
    }
}
