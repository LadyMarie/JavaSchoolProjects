package com.tsystems.JavaSchool.ShopOnline.Dao;

import javax.persistence.*;

/**
 * Created by asus on 13.02.2016.
 */
@Entity
public class Roles {
    @Id
    @GeneratedValue
    private long id;
    private String name;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name +
                '}';
    }
}
