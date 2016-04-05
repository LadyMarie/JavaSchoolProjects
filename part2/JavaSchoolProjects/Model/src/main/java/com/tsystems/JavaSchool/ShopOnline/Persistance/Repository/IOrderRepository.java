package com.tsystems.JavaSchool.ShopOnline.Persistance.Repository;

import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Order;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by asus on 14.03.2016.
 */
public interface IOrderRepository extends JpaRepository<Order,Long>{

    @Query("select o from Order o where o.user=:userParam and o.completed=false")
    Order getIncompletedOrder(@Param("userParam") Person user);

    @Query("select o from Order o where o.user=:userParam  and o.completed=true")
    List<Order> getUserOrders(@Param("userParam") Person user);

    @Query("select o from Order o where o.completed=true")
    List<Order> findAllCopleted();
}
