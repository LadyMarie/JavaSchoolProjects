package com.tsystems.JavaSchool.ShopOnline.Dao;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import java.util.List;

/**
 * Created by asus on 28.02.2016.
 */
@Entity
public class CartItem {

    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    @JoinColumn(name="product")
    private Product product;

    @ManyToOne
    @JoinColumn(name="booking")
    private Order order;

    private int amount;

    public Integer getAmount() {
        return amount;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrders() {
        return order;
    }

    public void setOrders(Order order) {
        this.order = order;
    }
}
