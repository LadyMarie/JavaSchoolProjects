package com.tsystems.JavaSchool.ShopOnline.Persistance.Repository;

import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Person;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p where p.email = :email and p.password = :pass")
    Person findByCredenitials(@Param("email") String category, @Param("pass") String pass);
}
