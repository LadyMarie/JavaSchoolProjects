package com.tsystems.JavaSchool.ShopOnline.Services;

import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Product;

/**
 * Created by asus on 22.03.2016.
 */
public interface IAddProductService {

    /**
     * adds product to db
     * @param product
     * @return id in db of saved product
     */
    public String addProductGetId(Product product);
}
