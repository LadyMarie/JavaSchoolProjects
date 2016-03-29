package com.tsystems.JavaSchool.ShopOnline.Persistance.Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by asus on 13.02.2016.
 */
@Entity
@Table(name="booking")
public class Order implements Serializable{
    @Id
    @GeneratedValue
    private long id;
    //this flag is checked if user haven't made order, but add smth to cart
    private boolean completed;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.ALL}, fetch=FetchType.EAGER)
    private List<CartItem> cartItems;

    @OneToOne
    @JoinColumn(name="user")
    private Person user;

    //Todo: make a normal tables from it
    private String payMethod;
    private String deliveryMethod;
    private String status;

    //Todo: make a normal table from it
    private String address;

    public long getId() {
        return id;
    }

    public void setId(long id) {this.id = id;}


   public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }


    public void setUser(Person user) {
        this.user = user;
    }
    public Person getUser() {
        return user;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean getCompleted() {
        return completed;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", completed=" + completed +
                ", cartItems=" + cartItems +
                ", user=" + user +
                ", payMethod='" + payMethod + '\'' +
                ", deliveryMethod='" + deliveryMethod + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
