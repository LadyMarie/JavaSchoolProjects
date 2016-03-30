package com.tsystems.JavaSchool.ShopOnline.Persistance.Dao;

import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Product;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Repository.IProductRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * Created by asus on 13.03.2016.
 */
public class ProductDAO implements IProductDAO {


    private Logger logger = Logger.getLogger(ProductDAO.class);

    @Autowired
    IProductRepository productRepository;

    /**
     * adds product to db
     * @param product
     * @return id in db of saved product
     */
    public String addProductGetId(Product product) {
        try {
            product = productRepository.saveAndFlush(product);
            return String.valueOf(product.getId());
        }
        catch (Exception ex) {
            logger.error("Can't add product to db. " + product.toString() + " " + ex);
            return null;
        }
    }

    /**
     * returns list of all products from db
     * @return
     */
    public List<Product> getCatalog() {
        try {
            return productRepository.findAll();
        }
        catch (Exception ex) {
            logger.error("Can't get catalog from db. " + ex);
            return null;
        }
    }

    /**
     * save product to db
     * @param product
     */
    public void saveProduct(Product product) {
        try {
            productRepository.saveAndFlush(product);
        }
        catch (Exception ex) {
            logger.error("Can't save product to db. " + product.toString() + " " + ex);
        }
    }

}
