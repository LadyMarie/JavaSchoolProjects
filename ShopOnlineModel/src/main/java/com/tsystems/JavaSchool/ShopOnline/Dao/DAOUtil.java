package com.tsystems.JavaSchool.ShopOnline.Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by asus on 22.02.2016.
 */
public class DAOUtil {

    static EntityManager em;
    static EntityManagerFactory emf;

    public static EntityManager GetEntityManager() {
        if (em == null)
            return getEntityManagerFactory().createEntityManager();
        return em;
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null)
            return emf = Persistence.createEntityManagerFactory("UserSchema");
        return emf;
    }

    public static void releaseEntityManager() {
        em.close();
        emf.close();
    }
}
