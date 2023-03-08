package org.training.walletpayment.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.training.walletpayment.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	List<Product> findByProductNameContainingIgnoreCase(String productName, Pageable pageable);

}
