package com.tsystems.JavaSchool.ShopOnline.Services;

import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Product;

import java.util.Map;

/**
 * Created by asus on 13.03.2016.
 */
public interface IGetCatalogService {

    /**
     * Get all products from db
     * @return Map<String, Product>, where String - string representation of
     * product id, which is value
     */
    public Map<String, Product> getCatalog();

}
