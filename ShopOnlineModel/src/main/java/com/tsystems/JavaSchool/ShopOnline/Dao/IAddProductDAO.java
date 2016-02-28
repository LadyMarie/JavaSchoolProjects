package com.tsystems.JavaSchool.ShopOnline.Dao;

import java.util.List;

/**
 * Created by asus on 21.02.2016.
 */
public interface IAddProductDAO {

    String addProductGetId(Product product);

    List<Product> getCatalog();

}
