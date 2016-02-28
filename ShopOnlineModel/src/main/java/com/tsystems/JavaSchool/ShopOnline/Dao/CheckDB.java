/**
 * Created by asus on 14.02.2016.
 * Help class, won't be used
 */
package com.tsystems.JavaSchool.ShopOnline.Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CheckDB {

    static EntityManager em;

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserSchema");
        em = emf.createEntityManager();
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
        Product product = testDB.createProduct("cat", 3000, "animals", "cutest", "3 kilo", "20 dm^3", 5);
        CartItem item = new CartItem();
        item.setProduct(product);
        item.setAmount(10);
        commit(item);
        List<CartItem> items = em.createQuery("select p from CartItem p", CartItem.class).getResultList();
        for (CartItem u : items) {
            System.out.println(u);
            //  String id = new AddProductDAO().addProductGetId(product);
        }
    }

    private static void commit(CartItem item) {
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
