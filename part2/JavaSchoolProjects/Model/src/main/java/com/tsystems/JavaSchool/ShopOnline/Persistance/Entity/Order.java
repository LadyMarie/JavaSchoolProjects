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

    private String payMethod;
    private String deliveryMethod;

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
}
