package com.tsystems.JavaSchool.ShopOnline.Services;

import com.tsystems.JavaSchool.ShopOnline.Dao.Product;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by asus on 12.03.2016.
 */
public class GetCatalogService1 implements IGetCatalogService {
    public Map<String, Product> getCatalog() {
        return new HashMap<String, Product>();
    }
}
