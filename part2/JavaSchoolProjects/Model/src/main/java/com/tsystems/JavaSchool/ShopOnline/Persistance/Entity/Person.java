package com.tsystems.JavaSchool.ShopOnline.Persistance.Entity;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Entity
public class Person implements Serializable{
    @Id
    @GeneratedValue
    private long id;

    @NotEmpty(message = "Please enter your email.")
    @Pattern(regexp=".*@.*[.].*", message="Email should look like 'name@domain'")
    @NotNull
    private String email;

    @NotEmpty(message = "Please enter your password.")
    @NotNull
    @Pattern(regexp = "^[0-9A-Za-z]+$", message = "Password should only contain letters and digits")
    private String password;

    @Pattern(regexp = "^[0-9]{1,2}$", message = "Day is incorrect")
    private String birthDay;
    private String birthMonth;
    @Pattern(regexp = "^[0-9]{4}$", message = "Year is incorrect")
    private String birthYear;
    private String name;
    private String surname;
    private String role;

    //This field represents role as checkbox in Ui
    @Transient
    private Boolean isEmployee;


    public long getId() {
        return id;
    }
    public void setId(long id) {this.id = id;}

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthDay() { return birthDay; }
    public void setBirthDay(String birthDay) { this.birthDay = birthDay; }

    public String getBirthYear() { return birthYear; }
    public void setBirthYear(String birthYear) { this.birthYear = birthYear; }

    public String getBirthMonth() { return birthMonth; }
    public void setBirthMonth(String birthMonth) { this.birthMonth = birthMonth; }

    public Boolean getIsEmployee() {
        return isEmployee;
    }
    public void setIsEmployee(Boolean isEmployee) {this.isEmployee = isEmployee;}

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "User{" +
                "birthDay=" + birthDay +
                ", birthYear='" + birthYear + '\'' +
                ", birthMonth='" + birthMonth + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id + '\'' +
                ", name=" + name + '\'' +
                ", surname=" + surname + '\'' +
                ", isEmployee=" + isEmployee + '\'' +
                '}';
    }


}
