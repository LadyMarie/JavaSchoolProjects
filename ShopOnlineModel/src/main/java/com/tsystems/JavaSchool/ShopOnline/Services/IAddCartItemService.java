package com.tsystems.JavaSchool.ShopOnline.Services;

import com.tsystems.JavaSchool.ShopOnline.Dao.CartItem;
import com.tsystems.JavaSchool.ShopOnline.Dao.Person;
import com.tsystems.JavaSchool.ShopOnline.Dao.Product;

import java.util.Map;

/**
 * Created by asus on 21.02.2016.
 */
public interface IAddCartItemService {

    Map<String,CartItem> addCartItem(Map<String, Product> products,
                                     Map<String, CartItem> cart, String id, Person user);
}
