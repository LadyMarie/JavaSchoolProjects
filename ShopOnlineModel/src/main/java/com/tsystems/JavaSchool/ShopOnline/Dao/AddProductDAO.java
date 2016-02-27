package com.tsystems.JavaSchool.ShopOnline.Dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * Created by asus on 21.02.2016.
 */
public class AddProductDAO implements IAddProductDAO {

    EntityManager em;

    public String addProductGetId(Product product) {
        em = DAOUtil.GetEntityManager();
        commit(product);
        Long id = product.getId();
        return id.toString();
    }


    private void commit(Product product) {
        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
    }

}

