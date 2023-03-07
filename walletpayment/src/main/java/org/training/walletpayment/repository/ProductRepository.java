package org.training.walletpayment.repository;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.training.walletpayment.entity.Product;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer>{
	List<Product> findProductByProductName(String productName, Pageable pageable);
}
