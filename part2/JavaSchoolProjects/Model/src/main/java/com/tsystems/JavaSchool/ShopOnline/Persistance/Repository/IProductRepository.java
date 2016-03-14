package com.tsystems.JavaSchool.ShopOnline.Persistance.Repository;

import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Long> {

}
