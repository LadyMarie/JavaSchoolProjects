package com.tsystems.JavaSchool.ShopOnline.Services;

import com.tsystems.JavaSchool.ShopOnline.Persistance.Dao.IProductDAO;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Dao.ProductDAO;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by asus on 13.03.2016.
 */
@Service
public class GetCatalogService implements IGetCatalogService{

    @Autowired
    IProductDAO productDAO;

    /**
     * Converts list of products from db to map, which is easier to view in jsp
     * @return ap<String, Product>, where String - string representation of
     * product id, which is value
     */
    public Map<String, Product> getCatalog() {
        List<Product> products = productDAO.getCatalog();
        return convertToMap(products);
    }

    private Map<String,Product> convertToMap(List<Product> products) {
        Map<String, Product> prodMap = new LinkedHashMap<String, Product>();
        for (Product product: products){
            prodMap.put(String.valueOf(product.getId()), product);
        }
        return prodMap;
    }
}
