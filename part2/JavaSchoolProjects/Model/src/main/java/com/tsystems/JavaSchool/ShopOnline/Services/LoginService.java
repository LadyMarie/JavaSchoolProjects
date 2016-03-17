package com.tsystems.JavaSchool.ShopOnline.Services;

import com.tsystems.JavaSchool.ShopOnline.Persistance.Dao.IPersonDAO;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Dao.IProductDAO;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Person;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.digest.DigestUtils;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by asus on 13.03.2016.
 */
@Service
public class LoginService implements ILoginService{

    @Autowired
    IPersonDAO personDAO;

    /**
     * Get person from db with credenitials given
     * @return person or null, if this person don't exist
     */
    public Person getPerson(String email, String pass) {
       return  personDAO.getPerson(email, DigestUtils.md5Hex(pass));
    }

}
