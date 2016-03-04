package com.tsystems.JavaSchool.ShopOnline.Dao;

import javax.persistence.*;

/**
 * Created by asus on 21.02.2016.
 */
public class SignupDAO implements ISignupDAO {

    EntityManager em;
    Person newPerson;

    /**
     * add person given to db, if not exist, or update existing one
     * (if person with these credenitials is already in db)
     * @param newPerson updated or created person
     */
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
        if (newPerson.getBirthDay() != null)
            oldPerson.setBirthDay(newPerson.getBirthDay());
        if (newPerson.getBirthYear() != null)
            oldPerson.setBirthYear(newPerson.getBirthYear());
        if (newPerson.getBirthMonth() != null)
            oldPerson.setBirthMonth(newPerson.getBirthMonth());
        oldPerson.setRole(newPerson.getRole());
        return oldPerson;
    }
}

