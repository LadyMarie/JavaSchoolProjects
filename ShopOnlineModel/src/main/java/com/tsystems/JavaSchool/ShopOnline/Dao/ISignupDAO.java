package com.tsystems.JavaSchool.ShopOnline.Dao;

/**
 * Created by asus on 21.02.2016.
 */
public interface ISignupDAO {

    /**
     * add person given to db, if not exist, or update existing one
     * (if person with these credenitials is already in db)
     * @param person updated or created person
     */
    void addOrUpdateUser(Person person);

}
