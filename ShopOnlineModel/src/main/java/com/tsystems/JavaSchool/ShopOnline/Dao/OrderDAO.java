package com.tsystems.JavaSchool.ShopOnline.Dao;

import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 01.03.2016.
 */
public class OrderDAO implements IOrderDAO{

    EntityManager em;
    private Logger logger = Logger.getLogger(OrderDAO.class);

    /**
     * gets incompleted order from db or creates new one if not exist
     * @return
     */
    public Order getIncompletedOrder(Person user) {

        em = DAOUtil.GetEntityManager();
        Query query =  em.createQuery("select o from Order o where o.user=:userParam and o.completed=false", Order.class);
        query.setParameter("userParam", user);
        try {
            logger.info("Find incompleted order, will add items to it");
            return (Order)query.getSingleResult();
        }
        catch (NoResultException ex) {
            logger.info("there aren't incompleted orders, create new one");
            return new Order();
        }
        catch (NonUniqueResultException ex) {
            logger.error("There are several incompleted order in db, check business logic!");
            return (Order)query.getResultList().get(0);
        }
    }


    /**
     * Commit order to db
     * @param order
     */
    public void commit(Order order) {
        try {
            tryCommit(order);
        }
        catch (Exception ex) {
            logger.error("Can't commit order! " +
                    ex.getMessage() + " " + ex.getStackTrace().toString());
        }
    }


    /**
     * Commit item to db
     * @param item
     */
    public void commit(CartItem item) {
        try {
            tryCommit(item);
        }
        catch (Exception ex) {
            logger.error("Can't commit order! " +
                    ex.getMessage() + " " + ex.getStackTrace().toString());
        }
    }

    private void tryCommit(CartItem item) {
        em.getTransaction().begin();
        em.merge(item);
        em.getTransaction().commit();
    }

    /**
     * Get cartItems list of order from db
     * @param order
     * @return
     */
    public List<CartItem> getCartItems(Order order) {
        em = DAOUtil.GetEntityManager();
        Query query =  em.createQuery("select c from CartItem c where c.order.id=:orderParam", CartItem.class);
        query.setParameter("orderParam", order.getId());
        try {
            logger.info("Find cartitem of this order");
            return (ArrayList<CartItem>)query.getResultList();
        }
        catch (NoResultException ex) {
            logger.info("there aren't carts for this order, return empty list");
            return new ArrayList<CartItem>();
        }
    }

    private void tryCommit(Order order) {
        em.getTransaction().begin();
        em.persist(order);
        em.getTransaction().commit();
    }
}
