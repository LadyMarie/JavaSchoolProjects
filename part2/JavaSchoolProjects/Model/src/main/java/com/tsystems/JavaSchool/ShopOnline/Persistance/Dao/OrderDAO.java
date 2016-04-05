package com.tsystems.JavaSchool.ShopOnline.Persistance.Dao;

import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.CartItem;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Order;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Person;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Repository.ICartItemRepository;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Repository.IOrderRepository;
import javafx.collections.transformation.SortedList;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 14.03.2016.
 */
public class OrderDAO implements IOrderDAO{

    Logger logger = Logger.getLogger(OrderDAO.class);

    @Autowired
    IOrderRepository orderRepository;

    @Autowired
    private ICartItemRepository cartItemRepository;


    /**
 * Add item to incompleted order of this user
 * @param item
 * @param user
 */
    public void updateCartDB(CartItem item, Person user) {
        logger.info("Saving item to db for user " + user + " Item: " + item.getProduct().getName());
        if (cartItemRepository.exists(item.getId())) {
            CartItem itemFromDB = cartItemRepository.findOne(item.getId());
            itemFromDB.setAmount(itemFromDB.getAmount() + 1);
            cartItemRepository.save(itemFromDB);
        }
        else {
        Order order = getIncompletedOrder(user);
        if (order != null) {
            order = fillNewOrder(order, user);
            item.setOrders(order);
            List<CartItem> ci = order.getCartItems();
            ci.add(item);
            order.setCartItems(ci);
            orderRepository.saveAndFlush(order);
            }

        }

    }

    /**
     * save order to db
     * @param order
     */
    public void saveOrder(Order order) {
        try {
            logger.info("Save order to db. " + order.toString());
            orderRepository.saveAndFlush(order);
        }
        catch (Exception ex) {
            logger.error("Can't save order to db. " + order.toString()+ " " + ex);
        }

    }

    /**
     * get list of orders for this user
     */
    public ArrayList<Order> getUserOrders(Person user) {
        try {
            logger.info("Get orders of user. " + user.toString());
            return tryGetOrders(user);
        }
        catch (NoResultException ex) {
            logger.info("There aren't orders for this user, return empty list.");
            return new ArrayList<Order>();
        }
        catch (Exception ex) {
            logger.error("Can't get orders for this user. " + ex);
            return null;
        }
    }

    /**
     * get all orders and sort it by alphabet user email
     */
    public ArrayList<Order> getOrders() {
        try {
            logger.info("Get all orders. ");
            return (ArrayList<Order>)orderRepository.findAllCopleted();
        }
        catch (Exception ex) {
            logger.error("Can't get orders. " + ex);
            return null;
        }
    }

    /**
     * get order with id given
     * @param id
     * @return
     */
    public Order getOrder(Long id) {
        try {
            return orderRepository.findOne(id);
        }
        catch (Exception ex) {
            logger.error("Can't get order by id " + id);
            return null;
        }
    }

    private ArrayList<Order> tryGetOrders(Person user) {
        ArrayList<Order> orders = (ArrayList<Order>)orderRepository.getUserOrders(user);
        if (orders == null)
            return new ArrayList<Order>();
        else
            return orders;
    }

    /**
     * Returns cart for this user (this in an order, marked as "incompleted")
     * @param user
     * @return
     */
    public Order getIncompletedOrder(Person user) {
        try {
            return tryGetIncompletedOrder(user);
        }
        catch (NoResultException ex) {
            logger.info("there aren't incompleted orders, create new one");
            return new Order();
        }
        catch (NonUniqueResultException ex) {
            logger.error("There are several incompleted order in db, check business logic!");
            return null;
        }
        catch (Exception ex) {
            logger.error("Can't get incompleted order from db. User: " + user.getEmail() + " " + ex);
            return null;
        }
    }

    private Order tryGetIncompletedOrder(Person user) {
        Order order = orderRepository.getIncompletedOrder(user);
        if (order == null)
            return new Order();
        else
            return order;
    }


    private Order fillNewOrder(Order order, Person user) {
        if (order.getUser() == null)
            order.setUser(user);
        if (order.getCompleted() != false)
            order.setCompleted(false);
        if (order.getCartItems() == null)
            order.setCartItems(new ArrayList<CartItem>());
        return order;
    }

}
