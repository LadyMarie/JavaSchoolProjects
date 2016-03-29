package com.tsystems.JavaSchool.ShopOnline;

import com.tsystems.JavaSchool.ShopOnline.Config.BeanConfig;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.CartItem;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Person;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Product;
import com.tsystems.JavaSchool.ShopOnline.Services.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by asus on 13.03.2016.
 */
@Component
public class Check {



    //Todo: make normal test from this
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);

        //checkGetCatalogService(context);
        //checkLoginService(context);
        //checkCartItemService(context);
        //checkAddOrUpdateUser(context);
        checkAddProduct(context);
    }


    private static void checkGetCatalogService(ApplicationContext context) {
        IGetCatalogService getCatalogService = (IGetCatalogService)context.getBean("getCatalogService");
        Map<String,Product> products = getCatalogService.getCatalog();
    }

    private static void checkLoginService(ApplicationContext context) {
        ILoginService loginService = (ILoginService)context.getBean("loginService");
        Person user = loginService.getPerson("George@gmail.com", "gpassword");
        Person notExistUser = loginService.getPerson("null","null");
    }

    private static void checkCartItemService(ApplicationContext context) {
        checkCartItemMergeCarts(context);
    }

    private static void checkCartItemMergeCarts(ApplicationContext context) {
        //init
        ILoginService loginService = (ILoginService)context.getBean("loginService");
        IGetCatalogService getCatalogService = (IGetCatalogService)context.getBean("getCatalogService");
        ICartItemService cartItemService = (ICartItemService)context.getBean("cartItemService");
        Map<String,Product> products = getCatalogService.getCatalog();
        Product product1 = products.get(String.valueOf(1));
        Product product6 = products.get(String.valueOf(6));
        Map<String,CartItem> oldCartNotEmpty = createOldCart(product1,product6);


        //test
        Person userWithCart = loginService.getPerson("Rose@gmail.com","rpassword");
        cartItemService.mergeCarts(userWithCart,oldCartNotEmpty);
        cartItemService.mergeCarts(userWithCart,null);

        Person userWithCompletedOrder = loginService.getPerson("George@gmail.com", "gpassword");
        cartItemService.mergeCarts(userWithCompletedOrder,oldCartNotEmpty);
        cartItemService.mergeCarts(userWithCompletedOrder,null);

        Person userWithoutCart = loginService.getPerson("July@gmail.com", "julypassword");
        cartItemService.mergeCarts(userWithoutCart,oldCartNotEmpty);
        cartItemService.mergeCarts(userWithoutCart,null);
    }


    private static Map<String,CartItem> createOldCart(Product product1, Product product6) {
        Map<String,CartItem> oldCart = new HashMap<String, CartItem>();
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product1);
        cartItem.setAmount(1);
        oldCart.put(String.valueOf(product1.getId()),cartItem);
        CartItem cartItem6 = new CartItem();
        cartItem6.setProduct(product6);
        cartItem6.setAmount(2);
        oldCart.put(String.valueOf(product6.getId()),cartItem6);
        return oldCart;
    }

    private static Map<String,CartItem> createExpectedCart(Product product1, Product product6) {
        Map<String,CartItem> oldCart = new HashMap<String, CartItem>();
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product1);
        cartItem.setAmount(1);
        oldCart.put(String.valueOf(product1.getId()),cartItem);
        CartItem cartItem6 = new CartItem();
        cartItem6.setProduct(product6);
        cartItem6.setAmount(3);
        oldCart.put(String.valueOf(product6.getId()),cartItem6);
        return oldCart;
    }

    private static void checkAddOrUpdateUser(ApplicationContext context) {
        //init
        Person newPerson = new Person();
        newPerson.setEmail("newemail@gmail.com");
        newPerson.setPassword("password");
        newPerson.setIsEmployee(false);
        ILoginService loginService = (ILoginService)context.getBean("loginService");
        Person existingPerson = loginService.getPerson("Rose@gmail.com", "rpassword");
        existingPerson.setName("RoseName");
        existingPerson.setIsEmployee(true);
        ISignupService signupService = (ISignupService)context.getBean("signupService");

        //test
        signupService.addOrUpdateUserDB(existingPerson);
        newPerson.setPassword(DigestUtils.md5Hex(newPerson.getPassword()));
        signupService.addOrUpdateUserDB(newPerson);
    }

    private static void checkAddProduct(ApplicationContext context) {
        //init
        Product product = new Product();
        product.setName("TestProduct");
        product.setPrice("2$");
        IAddProductService addProductService = (IAddProductService)context.getBean("addProductService");

        //test
        String id = addProductService.addProductGetId(product);
    }
}
