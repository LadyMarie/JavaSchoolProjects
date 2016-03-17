package com.tsystems.JavaSchool.ShopOnline.Persistance.Repository;

import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Roles, Long> {

}
