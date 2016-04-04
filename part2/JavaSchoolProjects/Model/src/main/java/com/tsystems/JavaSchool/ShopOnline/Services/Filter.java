package com.tsystems.JavaSchool.ShopOnline.Services;

import javax.validation.constraints.Pattern;

/**
 * Created by asus on 03.04.2016.
 *
 * DTO for filtering params
 */
public class Filter {

    private String category;

    @Pattern(regexp = "^$|^[1-9][0-9]?+$", message = "Incorrect:")
    private String fromPrice;

    @Pattern(regexp = "^$|^[1-9][0-9]?+$", message = "Incorrect:")
    private String toPrice;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFromPrice() {
        return fromPrice;
    }

    public void setFromPrice(String fromPrice) {
        this.fromPrice = fromPrice;
    }

    public String getToPrice() {
        return toPrice;
    }

    public void setToPrice(String toPrice) {
        this.toPrice = toPrice;
    }

    @Override
    public String toString() {
        return "Filter{" +
                "category='" + category + '\'' +
                ", fromPrice='" + fromPrice + '\'' +
                ", toPrice='" + toPrice + '\'' +
                '}';
    }
}
