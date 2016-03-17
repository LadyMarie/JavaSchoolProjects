package com.tsystems.JavaSchool.ShopOnline.Persistance.Repository;

import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by asus on 15.03.2016.
 */
public interface ICartItemRepository extends JpaRepository<CartItem,Long>{
}
