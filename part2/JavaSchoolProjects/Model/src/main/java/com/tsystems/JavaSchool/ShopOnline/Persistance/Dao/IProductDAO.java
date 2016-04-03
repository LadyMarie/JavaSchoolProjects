package com.tsystems.JavaSchool.ShopOnline.Persistance.Dao;

import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Product;
import com.tsystems.JavaSchool.ShopOnline.Services.Filter;

import java.util.List;

/**
 * Created by asus on 13.03.2016.
 */
public interface IProductDAO {

    /**
     * adds product to db
     * @param product
     * @return id in db of saved product
     */
    String addProductGetId(Product product);


    /**
     * returns list of all products from db
     * @return
     */
    List<Product> getCatalog();

    /**
     * save product to db
     * @param product
     */
    void saveProduct(Product product);

    /**
     * filter catalog by params in class Filter
     * @param filter
     * @return filtered catalog
     */
    List<Product> filterCatalog(Filter filter);
}
