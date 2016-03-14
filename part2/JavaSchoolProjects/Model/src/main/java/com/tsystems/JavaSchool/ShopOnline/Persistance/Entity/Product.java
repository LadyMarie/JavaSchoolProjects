package com.tsystems.JavaSchool.ShopOnline.Persistance.Entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by asus on 27.02.2016.
 */
@Entity
@Table(schema = "shoponlinejpa")
public class Product {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private Integer price;
    private String category;
    private String params;
    private String volume;
    private String weight;
    private Integer amount;

    public long getId() {
        return id;
    }
    public void setId(long id) {this.id = id;}

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public String getParams() {
        return params;
    }
    public void setParams(String params) {this.params = params;}

    public String getVolume() {
        return volume;
    }
    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getWeight() {
        return weight;
    }
    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Integer getAmount() {
        return amount;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
