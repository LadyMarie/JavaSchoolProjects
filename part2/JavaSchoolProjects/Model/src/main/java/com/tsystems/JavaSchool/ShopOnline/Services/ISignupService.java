package com.tsystems.JavaSchool.ShopOnline.Services;

import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Person;

/**
 * Created by asus on 18.03.2016.
 */
public interface ISignupService {

    /**
     * Save user to db or update existing user
     * @param user
     */
    public void addOrUpdateUserDB(Person user);
}
