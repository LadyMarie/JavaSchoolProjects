package com.tsystems.JavaSchool.ShopOnline.Services;

import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Product;

import java.util.Map;

/**
 * Created by asus on 22.03.2016.
 */
public interface IProductService {

    /**
     * adds product to db
     * @param product
     * @return id in db of saved product
     */
    public String addProductGetId(Product product);

    /**
     * Get all products from db
     * @return Map<String, Product>, where String - string representation of
     * product id, which is value
     */
    public Map<String, Product> getCatalog();

}
