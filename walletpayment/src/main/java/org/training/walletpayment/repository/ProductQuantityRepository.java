package org.training.walletpayment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.training.walletpayment.entity.ProductQuantity;

public interface ProductQuantityRepository extends JpaRepository<ProductQuantity, Integer> {

}