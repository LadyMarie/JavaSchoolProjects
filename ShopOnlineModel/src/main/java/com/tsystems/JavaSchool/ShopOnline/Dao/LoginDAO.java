package com.tsystems.JavaSchool.ShopOnline.Dao;

import javax.persistence.*;

/**
 * Created by asus on 18.02.2016.
 */
public class LoginDAO implements ILoginDAO {

    EntityManager em;

    public Person getPersonDB(String email, String password) {
        em = DAOUtil.GetEntityManager();
        Person p = null;
        //List<Roles> roles = em.createQuery("select r from Roles r", Roles.class).getResultList();
        Query query =  em.createQuery("select u from Person u where email=:emailParameter and password=:password", Person.class);
        query.setParameter("emailParameter", email);
        query.setParameter("password", password);
        try {
             p = (Person) query.getSingleResult();
        } catch (NoResultException e) {
            //no result
        }
        return p;
    }
}
