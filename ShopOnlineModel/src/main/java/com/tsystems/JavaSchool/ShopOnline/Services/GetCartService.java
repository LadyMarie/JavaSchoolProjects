package com.tsystems.JavaSchool.ShopOnline.Services;

import com.tsystems.JavaSchool.ShopOnline.Dao.CartItem;
import com.tsystems.JavaSchool.ShopOnline.Dao.Person;

import java.util.Map;

/**
 * Created by asus on 01.03.2016.
 */
public class GetCartService implements IGetCartService {

    public Map<String, CartItem> getCart(Person user) {
        //todo: from Order find where user=user and completed=false
        //take latest
        return null;
    }
}
