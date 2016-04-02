package com.tsystems.JavaSchool.ShopOnline.Services;

import com.tsystems.JavaSchool.ShopOnline.Persistance.Dao.IOrderDAO;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Dao.OrderDAO;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.CartItem;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Order;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Person;
import javafx.collections.transformation.SortedList;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created by asus on 04.03.2016.
 */
public class OrderService implements  IOrderService {

    Logger logger = Logger.getLogger(OrderComparator.class);

    @Autowired
    private IOrderDAO orderDAO;

    @Autowired
    private Comparator<Order> orderComparator;

    @Autowired
    private ICartItemService cartItemService;

    /**
     * set order to state "completed" and save its info to db
     */
    public void makeOrder(Order order) {
        order.setStatus("new");
        order.setCompleted(true);
        orderDAO.saveOrder(order);
    }

    /**
     * gets incompleted order from db or creates new one if not exist
     * @return
     */
    public Order getIncompletedOrder(Person user) {
        return orderDAO.getIncompletedOrder(user);
    }

    /**
     * get list of orders for this user
     */
    public ArrayList<Order> getUserOrders(Person user) {
        return orderDAO.getUserOrders(user);
    }

    /**
     * get all orders and sort it by alphabet user email
     */
    public ArrayList<Order> getOrders() {
        ArrayList<Order> orders = orderDAO.getOrders();
        orders.sort(orderComparator);
        return orders;
    }

    /**
     * change status of order with id given
     */
    public void changeStatus(String strId, String status) {
        logger.info("start");
        try {
            tryChangeStatus(strId,status);
        }
        catch(NumberFormatException ex) {
            logger.error("Can't parse order id " + strId + ". Probably" +
                    " someone accessed /status directly " + ex);
        }
    }

    private void tryChangeStatus(String strId, String status) {
        Long id = Long.parseLong(strId);
        Order order = orderDAO.getOrder(id);
        if (order != null) {
            order.setStatus(status);
            orderDAO.saveOrder(order);
        }
    }


    /**
     * repeat order with id given
     * @param orders
     * @param strId
     * @return
     */
    public Order repeatOrder(List<Order> orders, String strId) {
        try {
            return tryRepeatOrder(orders,strId);
        }
        catch (NumberFormatException ex) {
            logger.error("Can't parse id" + strId);
            return null;
        }
    }

    private Order tryRepeatOrder(List<Order> orders, String strId) {
        Long id = Long.parseLong(strId);
        Order order = getOrderById(orders, id);
        if (order == null) {
            logger.error("Can't get order from db with id " + id);
            return order;
        } else {
            Order copyOrder = copyOrder(order);
            makeOrder(copyOrder);
            logger.info("Order repeated successfully: " + order.toString());
            return copyOrder;
        }

    }


    private Order getOrderById(List<Order> orders, Long id) {
        for (Order order: orders) {
            if (order.getId() == id) {
                logger.info("User wants order " + order.toString());
                return order;
            }
        }
        logger.error("There isn't any order with id " + id);
        return null;
    }

    //copy to new object to avoid persistance exceptions
    private Order copyOrder(Order order) {
        Order copyOrder = new Order();
        copyOrder.setStatus(order.getStatus());
        copyOrder.setDeliveryMethod(order.getDeliveryMethod());
        copyOrder.setUser(order.getUser());
        copyOrder.setPayMethod(order.getPayMethod());
        copyOrder.setAddress(order.getAddress());
        copyOrder.setCompleted(order.getCompleted());
        List<CartItem> items = new ArrayList<CartItem>();
        for (CartItem item:order.getCartItems())
            items.add(copyCartItem(item));
        copyOrder.setCartItems(items);
        return copyOrder;
    }

    private CartItem copyCartItem(CartItem item) {
        CartItem copyCartItem = new CartItem();
        copyCartItem.setAmount(item.getAmount());
        copyCartItem.setProduct(item.getProduct());
        return copyCartItem;
    }


    /**
     * Get cart for order given
     * @param orderId
     * @param orders list of orders, previously got from db,
     *               because, if we saved this list in controller,
     *               we don't need to call db one more time
     * @return
     */
    public Map<String, CartItem> getCart(String orderId, ArrayList<Order> orders) {
        try {
            return tryGetCart(orders,orderId);
        }
        catch (NumberFormatException ex) {
            logger.error("Can't parse id " + orderId);
            return new HashMap<String, CartItem>();
        }
    }

    private Map<String,CartItem> tryGetCart(ArrayList<Order> orders, String orderId) {
        Long id = Long.parseLong(orderId);
        Order order = getOrderById(orders,id);
        if (order == null) {
            logger.error("Can't get order from db with id " + id);
            return new HashMap<String, CartItem>();
        } else
            return cartItemService.convertToMap(order.getCartItems());
    }
}
