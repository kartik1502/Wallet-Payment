package org.training.walletpayment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.training.walletpayment.entity.Cart;
import org.training.walletpayment.entity.ProductQuantity;

public interface ProductQuantityRepository extends JpaRepository<ProductQuantity, Integer> {

	List<ProductQuantity> findAllByCart(Cart cart);

}
