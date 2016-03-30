package com.tsystems.JavaSchool.ShopOnline;

import com.tsystems.JavaSchool.ShopOnline.Config.BeanConfig;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.CartItem;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Order;
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

        //checkproductService(context);
        //checkpersonService(context);
        //checkCartItemService(context);
        //checkAddOrUpdateUser(context);
        //checkAddProduct(context);
        checkMakeOrder(context);
    }

    private static void checkproductService(ApplicationContext context) {
        IProductService productService = (IProductService)context.getBean("productService");
        Map<String,Product> products = productService.getCatalog();
    }

    private static void checkpersonService(ApplicationContext context) {
        IPersonService personService = (IPersonService)context.getBean("personService");
        Person user = personService.getPerson("George@gmail.com", "gpassword");
        Person notExistUser = personService.getPerson("null","null");
    }

    private static void checkCartItemService(ApplicationContext context) {
        checkCartItemMergeCarts(context);
    }

    private static void checkCartItemMergeCarts(ApplicationContext context) {
        //init
        IPersonService personService = (IPersonService)context.getBean("personService");
        IProductService productService = (IProductService)context.getBean("productService");
        ICartItemService cartItemService = (ICartItemService)context.getBean("cartItemService");
        Map<String,Product> products = productService.getCatalog();
        Product product1 = products.get(String.valueOf(1));
        Product product6 = products.get(String.valueOf(6));
        Map<String,CartItem> oldCartNotEmpty = createOldCart(product1,product6);


        //test
        Person userWithCart = personService.getPerson("Rose@gmail.com","rpassword");
        cartItemService.mergeCarts(userWithCart,oldCartNotEmpty);
        cartItemService.mergeCarts(userWithCart,null);

        Person userWithCompletedOrder = personService.getPerson("George@gmail.com", "gpassword");
        cartItemService.mergeCarts(userWithCompletedOrder,oldCartNotEmpty);
        cartItemService.mergeCarts(userWithCompletedOrder,null);

        Person userWithoutCart = personService.getPerson("July@gmail.com", "julypassword");
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
        IPersonService personService = (IPersonService)context.getBean("personService");
        Person existingPerson = personService.getPerson("Rose@gmail.com", "rpassword");
        existingPerson.setName("RoseName");
        existingPerson.setIsEmployee(true);
        

        //test
        personService.addOrUpdateUserDB(existingPerson);
        newPerson.setPassword(DigestUtils.md5Hex(newPerson.getPassword()));
        personService.addOrUpdateUserDB(newPerson);
    }

    private static void checkAddProduct(ApplicationContext context) {
        //init
        Product product = new Product();
        product.setName("TestProduct");
        product.setPrice("2$");
        IProductService productService = (IProductService)context.getBean("productService");

        //test
        String id = productService.addProductGetId(product);
    }

    private static void checkMakeOrder(ApplicationContext context) {
        //init
        ICartItemService cartItemService = (ICartItemService)context.getBean("cartItemService");
        IPersonService personService = (IPersonService)context.getBean("personService");
        IProductService productService = (IProductService)context.getBean("productService");
        IOrderService orderService = (IOrderService)context.getBean("orderService");

        Map<String,Product> products = productService.getCatalog();
        Product product1 = products.get(String.valueOf(1));
        Product product6 = products.get(String.valueOf(10));
        int amountold = Integer.parseInt(product6.getAmount());
        Map<String,CartItem> cart = createOldCart(product1,product6);
        Person user = personService.getPerson("Rose@gmail.com","rpassword");

        //test
        cartItemService.addCartItem(products,cart,String.valueOf(product6.getId()),user);
        Product product6new = productService.getCatalog().get(String.valueOf(10));
        int diff = amountold - Integer.parseInt(product6new.getAmount());

        Order order = new Order();
        orderService.makeOrder(order);
        Person userGeorge = personService.getPerson("George@gmail.com","gpassword");
        Order order1 = orderService.getIncompletedOrder(userGeorge);
        order1.setDeliveryMethod("home");
        orderService.makeOrder(order1);
    }
}
