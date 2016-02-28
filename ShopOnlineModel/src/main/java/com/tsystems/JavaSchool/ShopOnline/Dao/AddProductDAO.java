package com.tsystems.JavaSchool.ShopOnline.Dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by asus on 21.02.2016.
 */
public class AddProductDAO implements IAddProductDAO {

    EntityManager em;

    public String addProductGetId(Product product) {
        em = DAOUtil.GetEntityManager();
        em.persist(product);
        Long id = product.getId();
        return id.toString();
    }


    public List<Product> getCatalog() {
        em = DAOUtil.GetEntityManager();
        List<Product> products = em.createQuery("select p from Product p", Product.class).getResultList();

        return products;
    }


}

