package com.tsystems.JavaSchool.ShopOnline.Dao;

import javax.persistence.*;

/**
 * Created by asus on 21.02.2016.
 */
public class SignupDAO implements ISignupDAO {

    EntityManager em;
    Person newPerson;

    public void addOrUpdateUser(Person newPerson) {
        em = DAOUtil.GetEntityManager();
        this.newPerson = newPerson;
        addRole();
        Person oldPerson = findPerson();
        if (oldPerson == null)
            commit(newPerson);
        else
            commit(mergePerson(oldPerson));
    }

    private Person addRole() {
        String strRole = newPerson.getStrRole();
        if (strRole != null)
            newPerson.setRole(makeRole(strRole));
        return newPerson;
    }


    private Roles makeRole(String role) {
            Query query = em.createQuery("select u from Roles u where u.name=:roleParameter", Roles.class);
            query.setParameter("roleParameter", role);
            return (Roles)query.getResultList().get(0);
        }


    private Person findPerson() {
        Query query =  em.createQuery("select u from Person u where email=:emailParameter and password=:password", Person.class);
        query.setParameter("emailParameter", newPerson.getEmail());
        query.setParameter("password", newPerson.getPassword());
        try {
            return (Person)query.getSingleResult();
        }
        catch (NoResultException ex) {
            return null;
        }
    }

    private void commit(Person person) {
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
    }

    private Person mergePerson(Person oldPerson) {
        if (newPerson.getName() != null)
            oldPerson.setName(newPerson.getName());
        if (newPerson.getSurname() != null)
            oldPerson.setSurname(newPerson.getSurname());
        if (newPerson.getBirthdate() != null)
            oldPerson.setBirthdate(newPerson.getBirthdate());
        oldPerson.setRole(newPerson.getRole());
        return oldPerson;
    }
}