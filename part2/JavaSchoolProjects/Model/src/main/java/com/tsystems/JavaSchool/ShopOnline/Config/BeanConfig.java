package com.tsystems.JavaSchool.ShopOnline.Config;

import com.tsystems.JavaSchool.ShopOnline.Persistance.Dao.*;
import com.tsystems.JavaSchool.ShopOnline.Services.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

/**
 * Created by asus on 14.03.2016.
 */
@Configuration
@Import(DBConfig.class)
public class BeanConfig {

    @Bean
    @Scope("singleton")
    public IProductDAO productDAO() {
        return new ProductDAO();
    }

    @Bean
    @Scope("singleton")
    public IOrderDAO orderDAO() {
        return new OrderDAO();
    }

    @Bean
    @Scope("singleton")
    public IPersonDAO personDAO() {
        return new PersonDAO();
    }


    @Bean
    @Scope("singleton")
    public IPersonService loginService() {
        return new PersonService();
    }

    @Bean
    @Scope("singleton")
    public ICartItemService cartItemService() {
        return new CartItemService();
    }


    @Bean
    @Scope("singleton")
    public IProductService productService() {
        return new ProductService();
    }

    @Bean
    @Scope("singleton")
    public IOrderService orderService() {
        return new OrderService();
    }
}
