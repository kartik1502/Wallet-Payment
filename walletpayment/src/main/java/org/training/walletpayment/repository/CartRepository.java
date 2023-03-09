package org.training.walletpayment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.training.walletpayment.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

   
}
