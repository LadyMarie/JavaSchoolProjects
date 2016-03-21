package com.tsystems.JavaSchool.ShopOnline.Persistance.Dao;

import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Person;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Product;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Repository.IPersonRepository;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Repository.IProductRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;


/**
 * Created by asus on 13.03.2016.
 */
public class PersonDAO implements IPersonDAO {


    private Logger logger = Logger.getLogger(PersonDAO.class);

    @Autowired
    IPersonRepository personRepository;


    /**
     * Get person from db with credenitials given
     * @return person or null, if this person don't exist
     */
    public Person getPerson(String email, String pass) {
        try {
            logger.info("Get person from db. " + email + " " + pass);
            return personRepository.findByCredenitials(email,pass);
        }
        catch (Exception ex) {
            logger.error("Can't get person by credenitials from db. " + ex);
            return null;
        }
    }

    /**
     * Save user to db or update existing user
     * @param user
     */
    public void addOrUpdateUser(Person user) {
        try {
            logger.info("Save person to db. " + user.toString());
            addRole(user);
            personRepository.saveAndFlush(user);
        }
        catch (Exception ex) {
            logger.error("Can't save person to db. " + user.toString()+ " " + ex);
        }
    }

    private void addRole(Person user) {
        if (user.getIsEmployee())
            user.setRole("Employee");
        else
            user.setRole("Client");
    }



}
