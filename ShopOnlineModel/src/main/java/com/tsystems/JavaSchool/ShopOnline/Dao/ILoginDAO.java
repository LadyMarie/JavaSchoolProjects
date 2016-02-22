package com.tsystems.JavaSchool.ShopOnline.Dao;

/**
 * Created by asus on 18.02.2016.
 */
public interface ILoginDAO {

   Person getPersonDB(String email, String password);
}
