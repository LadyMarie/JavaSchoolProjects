package com.tsystems.JavaSchool.ShopOnline.Persistance.Entity;
import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by asus on 27.02.2016.
 */
@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    @Pattern(regexp = "^[0-9]+$", message = "Incorrect price")
    private String price;
    private String category;
    private String params;
    private String volume;
    private String weight;
    @Pattern(regexp = "^[0-9]+$", message = "Incorrect amount")
    private String amount;

    //This field stores path to pic file in client's machine, and is used to upload pic/
    //But there is no need to put it into db, because pic names are product_id.jpeg
    @Transient
    MultipartFile pic;

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

    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
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

    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }

    public MultipartFile getPic() {
        return pic;
    }
    public void setPic(MultipartFile pic) {
        this.pic = pic;
    }


    @Override
    public String toString() {
        return "Product{" +
                "name=" + name +
                ", price='" + price + '\'' +
                ", category='" + category + '\'' +
                ", params='" + params + '\'' +
                ", id=" + id + '\'' +
                ", volume=" + volume + '\'' +
                ", weight=" + weight + '\'' +
                ", amount=" + amount + '\'' +
                '}';
    }
}
