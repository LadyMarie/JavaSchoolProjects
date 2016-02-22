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

        Person us = testDB.createUser("Salma@yandex.ru","verySecure",testDB.getDate(1,Calendar.DECEMBER,2000),"Employee", "Salma", "Hayek");
        (new SignupDAO()).addOrUpdateUser(us);
        Person newUs = testDB.createUser("Salma@yandex.ru","verySecure",testDB.getDate(1,Calendar.DECEMBER,2000),"Employee", "Dolly", "Sheep");
        (new SignupDAO()).addOrUpdateUser(newUs);

        //System.out.println(p.getEmail());

      /*  for(Person u:users){
            System.out.println(u);
        }*/
    }

    private Person createUser(String email, String password, Date date, String role, String name, String surname) {
        Person person = new Person();
        person.setEmail(email);
        person.setPassword(password);
        person.setBirthdate(date);
        person.setStrRole(role);
        person.setName(name);
        person.setSurname(surname);
        return person;
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

    private void addUser(String email, String password, Date date, String role) {
        Person person = new Person();
        person.setEmail(email);
        person.setPassword(password);
        person.setBirthdate(date);
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