package com.tsystems.JavaSchool.ShopOnline;

import com.tsystems.JavaSchool.ShopOnline.Config.ProductConfig;
import com.tsystems.JavaSchool.ShopOnline.Services.IGetCatalogService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by asus on 13.03.2016.
 */
@Component
public class Check {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(ProductConfig.class);
        IGetCatalogService getCatalogService = (IGetCatalogService)context.getBean("getCatalogService");
        getCatalogService.getCatalog();
    }
}
