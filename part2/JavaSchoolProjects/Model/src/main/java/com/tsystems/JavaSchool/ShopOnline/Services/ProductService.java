package com.tsystems.JavaSchool.ShopOnline.Services;

import com.tsystems.JavaSchool.ShopOnline.Persistance.Dao.IProductDAO;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Product;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by asus on 22.03.2016.
 */
public class ProductService implements IProductService {

    @Autowired
    IProductDAO productDAO;


    /**
     * adds product to db
     * @param product
     * @return id in db of saved product
     */
    public String addProductGetId(Product product) {
        return productDAO.addProductGetId(product);
    }

    /**
     * Converts list of products from db to map, which is easier to view in jsp
     * @return ap<String, Product>, where String - string representation of
     * product id, which is value
     */
    public Map<String, Product> getCatalog() {
        List<Product> products = productDAO.getCatalog();
        return convertToMap(products);
    }

    /**
     * filter catalog by params in class Filter
     * @param filter
     * @return filtered catalog
     */
    public Map<String, Product> filterCatalog(Filter filter) {
        List<Product> products = productDAO.filterCatalog(filter);
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
