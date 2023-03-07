package org.training.walletpayment.service;

import java.util.List;

import org.training.walletpayment.entity.Product;

public interface ProductService {

	List<Product> getAllProducts(Integer pageNo, Integer pageSize, String sortBy);
	
	
}
