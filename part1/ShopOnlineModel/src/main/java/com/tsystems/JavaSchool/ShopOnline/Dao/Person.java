package com.tsystems.JavaSchool.ShopOnline.Dao;

import javax.persistence.*;

@Entity
public class Person {
    @Id
    @GeneratedValue
    private long id;
    private String email;
    private String password;
    private String birthDay;
    private String birthMonth;
    private String birthYear;
    private String name;
    private String surname;
    private String strRole;

//    @OneToMany
//    @JoinColumn(name="orders")
//    private Order orders;

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

    public Roles getRole() {
        return role;
    }
    public void setRole(Roles role) { this.role = role; }

    public void setStrRole(String role) {this.strRole = role;}

    //this strange field we need to allow servlet pass role name, knowing nothing about db
    @Transient
    public String getStrRole() {
        return strRole;
    }

    @Override
    public String toString() {
        return "User{" +
                "birthDay=" + birthDay +
                ", birthYear='" + birthYear + '\'' +
                ", birthMonth='" + birthMonth + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role.toString() +
                '}';
    }
}
