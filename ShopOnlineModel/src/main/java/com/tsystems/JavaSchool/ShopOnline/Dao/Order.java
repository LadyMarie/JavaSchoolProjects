package com.tsystems.JavaSchool.ShopOnline.Dao;

import javax.persistence.*;
import java.util.List;

/**
 * Created by asus on 13.02.2016.
 */
@Entity
@Table(name="booking")
public class Order {
    @Id
    @GeneratedValue
    private long id;
    //this flag is checked if user haven't made order, but add smth to cart
    private boolean completed;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.ALL})
    private List<CartItem> cartItems;

    @OneToOne
    @JoinColumn(name="user")
    private Person user;

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
}
