package com.tsystems.JavaSchool.ShopOnline.Services;

import com.tsystems.JavaSchool.ShopOnline.Persistance.Dao.IProductDAO;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Product;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by asus on 22.03.2016.
 */
public class AddProductService implements IAddProductService {

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
}
