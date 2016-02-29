package com.tsystems.JavaSchool.ShopOnline.Dao;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by asus on 18.02.2016.
 */
//@Entity
public class Order {
    @Id
    @GeneratedValue
    private long id;
    private String email;
    //todo: make regex
    private String password;
    private Date birthdate;
    private String name;
    private String surname;
    //this flag is checked if user haven't made order, but add smth to cart
    private boolean completed;

    @OneToMany
    @JoinColumn(name="cartItems")
    private List<CartItem> cartItems;

    @OneToOne
    @JoinColumn(name="user")
    private Person user;

    public long getId() {
        return id;
    }

    public void setId(long id) {this.id = id;}

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "User{" +
                "birthdate=" + birthdate.toString() +
                ", email='" + email + '\'' +
                ", id=" + id + '\'' +
                ", password='" + password + '\'' +
                ", user=" + user.toString() +
                '}';
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public void setUser(Person user) {
        this.user = user;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
