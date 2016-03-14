/**
 * Created by asus on 14.02.2016.
 * Help class, won't be used
 */
package com.tsystems.JavaSchool.ShopOnline.Dao;

import com.tsystems.JavaSchool.ShopOnline.Services.AddCartItemService;
import com.tsystems.JavaSchool.ShopOnline.Services.GetCartService;
import com.tsystems.JavaSchool.ShopOnline.Services.IAddCartItemService;
import com.tsystems.JavaSchool.ShopOnline.Services.IGetCartService;

import javax.persistence.*;
import java.util.*;

public class CheckDB {

    static EntityManager em;

    public static void main(String[] args) {
      //  EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserSchema");
      //  em = emf.createEntityManager();
        em  = DAOUtil.GetEntityManager();
        CheckDB testDB = new CheckDB();
       /* testDB.fillRolesTable();
        testDB.addUser("NewEmail@yandex.ru","verySecure",testDB.getDate(1,Calendar.DECEMBER,2000),"Employee");
        List<Roles> roles = em.createQuery("select r from Roles r", Roles.class).getResultList();
        List<Person> users = em.createQuery("select u from Person u", Person.class).getResultList();

        Query query =  em.createQuery("select u from Person u where email=:emailParameter and password=:password", Person.class);
        query.setParameter("emailParameter", "NewEmail@yandex.ru");
        query.setParameter("password", "verySecure");
       // Person p = (Person) query.getSingleResult();*/

     /*   Person us = testDB.createUser("Salma@yandex.ru","verySecure","1","2000","December","Employee", "Salma", "Hayek");
        (new SignupDAO()).addOrUpdateUser(us);
        Person newUs = testDB.createUser("Salma@yandex.ru","verySecure","1","2000","December","Employee", "Dolly", "Sheep");
        (new SignupDAO()).addOrUpdateUser(newUs);*/

        //System.out.println(p.getEmail());

      /*  for(Person u:users){
            System.out.println(u);
        }*/
        /*Product product = testDB.createProduct("cat", 3000, "animals", "cutest", "3 kilo", "20 dm^3", 5);
        CartItem item = new CartItem();
        item.setProduct(product);
        item.setAmount(10);
        commit(item);
        List<CartItem> items = em.createQuery("select p from CartItem p", CartItem.class).getResultList();
        for (CartItem u : items) {
            System.out.println(u);
            //  String id = new AddProductDAO().addProductGetId(product);
        }*/

       /* Query query =  em.createQuery("select p from Product p where name=:nameParameter", Product.class);
        query.setParameter("nameParameter", "cat");
        Product product = (Product) query.getResultList().get(0);
        CartItem item = new CartItem();
        item.setProduct(product);
        item.setAmount(10);
        commit(item);*/

        Map<String,Product> products = new AddProductDAO().getCatalog();

        /*Student student = new Student();
        student.setFirstName("trrt");

        List<EntranceAttemp> attl = new ArrayList<EntranceAttemp>();
        EntranceAttemp att = new EntranceAttemp();
        att.setNote(0);
        att.setStudent(student);
        EntranceAttemp att1 = new EntranceAttemp();
        att1.setNote(1);
        att1.setStudent(student);
        attl.add(att);
        attl.add(att1);
        student.setAttemps(attl);
        commit(student);
        Query query = em.createQuery("select s from Student s", Student.class);
        List<Student> st = query.getResultList();
        List<EntranceAttemp> attq = st.get(0).getAttemps();*/



        Query query =  em.createQuery("select u from Person u where email=:emailParameter and password=:password", Person.class);
        query.setParameter("emailParameter", "NewEmail@yandex.ru");
        query.setParameter("password", "verySecure");
        Person p = (Person) query.getResultList().get(0);



        //add smth to order table to create it
        Order order = testDB.createOrder(products, p, testDB);
       /* em  = DAOUtil.GetEntityManager();
        query =  em.createQuery("select o from Order o where o.user=:userParam and o.completed=false", Order.class);
        query.setParameter("userParam", p);
        Order order1 = (Order)query.getSingleResult();
        IOrderDAO orderDAO = new OrderDAO();
      //  Order order1 = testDB.getIncompletedOrder(p);

        CartItem item = new CartItem();
        item.setAmount(4);
        item.setOrders(order1);
        testDB.commit(item);
        item.setAmount(5);
        testDB.commit(item);*/






       /* List<CartItem> cartItemsNew = new ArrayList<CartItem>();
        CartItem item = new CartItem();
        item.setOrders(order);
        item.setProduct(products.get(String.valueOf(5)));
        item.setAmount(1);
        cartItemsNew.add(item);
        order.setCartItems(cartItemsNew);
        testDB.commit(order);
        query =  em.createQuery("select o from Order o where o.user=:userParam and o.completed=false", Order.class);
        query.setParameter("userParam", p);
        Order order1 = (Order)query.getSingleResult();
        item.setOrders(order1);
        item.setAmount(2);
        List<CartItem> cartItemsNew1 = new ArrayList<CartItem>();
        cartItemsNew1.add(item);
        order.setCartItems(cartItemsNew1);
        testDB.commit(order1);*/






        //add smth to order table to create it
       // Order order = testDB.createOrder(products, p);
        testDB.commit(order);

        //empty
        IGetCartService getCart = new GetCartService();
        Map<String,CartItem> cartItemsEmpty = getCart.getCart(p);

        //new cart item
        Map<String,CartItem> cartItems = new HashMap<String, CartItem>();
        IAddCartItemService addCartItem = new AddCartItemService();
        addCartItem.addCartItem(products,cartItems,String.valueOf(2),p);

        //existing cart item
        addCartItem.addCartItem(products,cartItems,String.valueOf(3),p);

        //not empty
        Map<String,CartItem> cartItemsNotEmpty = getCart.getCart(p);

        //merge carts
        Map<String,CartItem> cartItemsNotAuth = new HashMap<String, CartItem>();
        cartItemsNotAuth = addCartItem.addCartItem(products,cartItemsNotAuth,String.valueOf(3),null);
        Map<String,CartItem> mm = addCartItem.mergeCarts(p,cartItemsNotAuth,products);
    }

    public Order getIncompletedOrder(Person user) {


        Query query =  em.createQuery("select o from Order o where o.user=:userParam and o.completed=false", Order.class);
        query.setParameter("userParam", user);
        Order order = (Order)query.getSingleResult();
        return order;

    }


  /* private static void commit(Student student) {
        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();
    }*/

    private  Order createOrder(Map<String,Product> products, Person p, CheckDB testDB) {
        Order order = new Order();
        order.setCompleted(false);
        order.setUser(p);
        testDB.commit(order);
        CartItem firstCartItem = new CartItem();
        firstCartItem.setAmount(3);
        firstCartItem.setOrders(order);
        testDB.commit(firstCartItem);
      /*  CartItem secondCartItem = new CartItem();
        secondCartItem.setProduct(products.get(String.valueOf(2)));
        secondCartItem.setAmount(5);
        secondCartItem.setOrders(order);*/
      //  testDB.commit(secondCartItem);
      //  List<CartItem> cartItemsTest = new ArrayList<CartItem>();
        //cartItemsTest.add(firstCartItem);
       // cartItemsTest.add(secondCartItem);
      //  order.setCartItems(cartItemsTest);
        return order;
    }

    private  void commit(Order order) {
        em.getTransaction().begin();
        em.persist(order);
        em.getTransaction().commit();
    }

    private  void commit(CartItem item) {
        em.getTransaction().begin();
        em.persist(item);
        em.getTransaction().commit();
    }

    private Person createUser(String email, String password, String day, String year, String month, String role, String name, String surname) {
        Person person = new Person();
        person.setEmail(email);
        person.setPassword(password);
        person.setBirthDay(day);
        person.setBirthYear(year);
        person.setBirthMonth(month);
        person.setStrRole(role);
        person.setName(name);
        person.setSurname(surname);
        return person;
    }

    private Product createProduct(String name, Integer price, String category, String param,
                                  String weight, String volume, Integer amount) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setCategory(category);
        product.setParams(param);
        product.setWeight(weight);
        product.setVolume(volume);
        product.setName(name);
        product.setAmount(amount);
        return product;
    }

    private void fillRolesTable() {
        addRole("Employee");
        addRole("Client");
    }

    private void addRole(String name) {
        Roles role = new Roles();
        role.setName(name);
        em.getTransaction().begin();
        em.persist(role);
        em.getTransaction().commit();
    }

    private Date getDate(int day, int month, int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH,day);
        cal.set(Calendar.MONTH,month);
        cal.set(Calendar.YEAR,year);
        return cal.getTime();
    }

    private void addUser(String email, String password, String day, String year, String month, String role) {
        Person person = new Person();
        person.setEmail(email);
        person.setPassword(password);
        person.setBirthDay(day);
        person.setBirthYear(year);
        person.setBirthMonth(month);
        person.setRole(getRole(role));
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
    }

    private Roles getRole(String role) {
        Query query = em.createQuery("select u from Roles u where u.name=:roleParameter", Roles.class);
        query.setParameter("roleParameter", role);
        return (Roles)query.getResultList().get(0);
    }

}
