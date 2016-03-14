package com.tsystems.JavaSchool.ShopOnline.Dao;

/**
 * Created by asus on 18.02.2016.
 */
public interface ILoginDAO {

   /**
    * try to get person from db with this credenitials
    * @param email
    * @param password
    * @return person with credenitials given, or null, if not exist
    */
   Person getPersonDB(String email, String password);
}
