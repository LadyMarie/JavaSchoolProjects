package com.tsystems.JavaSchool.ShopOnline.Services;

import com.tsystems.JavaSchool.ShopOnline.Persistance.Dao.IPersonDAO;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Person;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by asus on 18.03.2016.
 */
public class SignupService implements ISignupService {

    @Autowired
    IPersonDAO personDAO;

    /**
     * Save user to db or update existing user
     * @param user
     */
    public void addOrUpdateUserDB(Person user) {
        encryptPassword(user);
        personDAO.addOrUpdateUser(user);
    }

    private void encryptPassword(Person user) {
       user.setPassword(DigestUtils.md5Hex(user.getPassword()));
    }
}
