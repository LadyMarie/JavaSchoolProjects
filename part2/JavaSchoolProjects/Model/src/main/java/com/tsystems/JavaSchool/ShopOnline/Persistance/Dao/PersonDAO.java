package com.tsystems.JavaSchool.ShopOnline.Persistance.Dao;

import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Person;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Product;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Repository.IPersonRepository;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Repository.IProductRepository;
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
            return personRepository.findByCredenitials(email,pass);
        }
        catch (Exception ex) {
            logger.error("Can't get person by credenitials from db. " +
                    ex.getMessage() + " " + ex.getStackTrace().toString());
            return null;
        }
    }


}
