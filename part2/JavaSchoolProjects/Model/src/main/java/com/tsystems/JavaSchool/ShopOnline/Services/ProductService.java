package com.tsystems.JavaSchool.ShopOnline.Services;

import com.tsystems.JavaSchool.ShopOnline.Persistance.Dao.IProductDAO;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Product;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created by asus on 22.03.2016.
 */
public class ProductService implements IProductService {

    @Autowired
    IProductDAO productDAO;

    Logger logger = Logger.getLogger(ProductService.class);


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
        List<Product> products = makeFilteredCatalog(filter);
        return convertToMap(products);
    }


    private List<Product> makeFilteredCatalog(Filter filter) {
        int fromPrice = getPrice(filter.getFromPrice());
        int toPrice = getPrice(filter.getToPrice());
        //if it is zero, it means, user hasn't input it. So interpret this as no top border
        //and take a very big number.
        if (toPrice == 0)
            toPrice = 1000000000;
        List<Product> products = getCategorizedProducts(filter);
        return  getFilteredProducts(fromPrice, toPrice, products);
    }

    private int getPrice(String strPrice) {
        try {
            return tryGetPrice(strPrice);
        }
        catch (NumberFormatException ex) {
            logger.error("Can't parse price. " + strPrice + " " + ex);
            return 0;
        }
    }

    private int tryGetPrice(String strPrice) {
        if ((strPrice == null) && (strPrice.equals("")))
            return 0;
        else
            return Integer.parseInt(strPrice);
    }

    private List<Product> getCategorizedProducts(Filter filter) {
        if ((filter.getCategory() == null) || (filter.getCategory().equals(""))) {
            return productDAO.getCatalog();
        } else {
            return productDAO.filterCatalog(filter);
        }
    }

    private List<Product> getFilteredProducts(int fromPrice, int toPrice, List<Product> products) {
        if (toPrice >= fromPrice)
            return filter(products,fromPrice,toPrice);
        else {
            logger.error("toPrice is smaller, then fromPrice," +
                    " return empty list. FromPrice:" + fromPrice + ", toPrice" + toPrice);
            return new ArrayList<Product>();
        }
    }


    private List<Product> filter(List<Product> products, int fromPrice, int toPrice) {
        List<Product> result = new ArrayList<Product>();
        for (Product product:products) {
            if (product.getPrice() == null) {
                logger.error("Price is null, but add this product to result " + product.toString());
                result.add(product);
            }
            else {
                int price = Integer.parseInt(product.getPrice());
                if ((price >= fromPrice) && (price <= toPrice))
                    result.add(product);
            }
        }
        return result;
    }

    private Map<String,Product> convertToMap(List<Product> products) {
        Map<String, Product> prodMap = new LinkedHashMap<String, Product>();
        for (Product product: products){
            prodMap.put(String.valueOf(product.getId()), product);
        }
        return prodMap;
    }
}
