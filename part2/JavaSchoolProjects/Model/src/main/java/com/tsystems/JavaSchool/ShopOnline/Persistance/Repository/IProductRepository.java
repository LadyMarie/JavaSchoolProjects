package com.tsystems.JavaSchool.ShopOnline.Persistance.Repository;

import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Person;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p where p.category = :category")
    List<Product> getCategoryOrders(@Param("category") String category);
}
