package org.training.foodorderapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.training.foodorderapplication.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

}
