package org.training.walletpayment.service;

import java.util.List;

import javax.validation.constraints.Size;

import org.training.walletpayment.dto.ProductDto;
import org.training.walletpayment.entity.Product;

public interface ProductService {
	List<Product> getAllProducts();

	void saveAll(List<Product> productList);

	List<ProductDto> findByProductNameContaining(@Size(min = 3) String productName, int page, int pageSize);

}