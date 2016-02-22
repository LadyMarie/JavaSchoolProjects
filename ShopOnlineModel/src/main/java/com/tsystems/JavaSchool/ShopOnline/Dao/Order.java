package com.tsystems.JavaSchool.ShopOnline.Dao;

import javax.persistence.*;
import java.util.Date;

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

    @OneToMany
    @JoinColumn(name="orders")
    private Order orders;

    @OneToOne
    @JoinColumn(name="role")
    private Roles role;

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

    public void setRole(Roles role) {
        this.role = role;
    }

    public Roles getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "User{" +
                "birthdate=" + birthdate.toString() +
                ", email='" + email + '\'' +
                ", id=" + id + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role.toString() +
                '}';
    }
}
