package com.tsystems.JavaSchool.ShopOnline.Dao;

import java.util.List;
import java.util.Map;

/**
 * Created by asus on 21.02.2016.
 */
public interface IAddProductDAO {

    String addProductGetId(Product product);

    Map<String, Product> getCatalog();

}
