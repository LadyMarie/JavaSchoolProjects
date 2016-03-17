package com.tsystems.JavaSchool.ShopOnline.Persistance.Dao;

import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Person;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Product;

import java.util.List;

/**
 * Created by asus on 13.03.2016.
 */
public interface IPersonDAO {


    /**
     * Get person from db with credenitials given
     * @return person or null, if this person don't exist
     */
    Person getPerson(String email, String pass);

}
