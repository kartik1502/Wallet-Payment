package org.training.walletpayment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.training.walletpayment.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
