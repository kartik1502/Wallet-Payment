package org.training.walletpayment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.training.walletpayment.entity.Cart;
import org.training.walletpayment.entity.User;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    Optional<Cart> findCartByCartIdAndAndUser(int cartId, User user);
}
