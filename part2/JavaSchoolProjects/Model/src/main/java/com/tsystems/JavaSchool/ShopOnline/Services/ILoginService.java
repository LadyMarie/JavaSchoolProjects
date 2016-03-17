package com.tsystems.JavaSchool.ShopOnline.Services;

import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Person;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Product;

import java.util.Map;

/**
 * Created by asus on 13.03.2016.
 */
public interface ILoginService {

    /**
     * Get person from db with credenitials given
     * @return person or null, if this person don't exist
     */
    public Person getPerson(String email, String pass);

}
