package com.tsystems.JavaSchool.ShopOnline.Dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by asus on 21.02.2016.
 */
public class AddProductDAO implements IAddProductDAO {

    EntityManager em;

    /**
     * adds product to db
     * @param product
     * @return id in db of saved product
     */
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

    /**
     * returns list of all products from db
     * @return
     */
    public Map<String, Product> getCatalog() {
        em = DAOUtil.GetEntityManager();
        List<Product> products = em.createQuery("select p from Product p", Product.class).getResultList();
        Map<String, Product> prodMap = new LinkedHashMap<String, Product>();
        for (Product product: products){
            prodMap.put(String.valueOf(product.getId()), product);
        }
        return prodMap;
    }


}

