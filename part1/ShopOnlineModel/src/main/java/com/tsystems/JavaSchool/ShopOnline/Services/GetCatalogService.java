package com.tsystems.JavaSchool.ShopOnline.Services;

import com.tsystems.JavaSchool.ShopOnline.Dao.DAOUtil;
import com.tsystems.JavaSchool.ShopOnline.Dao.Product;

import java.util.Map;

/**
 * Created by asus on 12.03.2016.
 */
public class GetCatalogService implements IGetCatalogService {

    /**
     * Get all products from db
     * @return Map<String, Product>, where String - string representation of
     * product id, which is value
     */
    public Map<String, Product> getCatalog() {
        return DAOUtil.getAddProductDao().getCatalog();
    }
}
