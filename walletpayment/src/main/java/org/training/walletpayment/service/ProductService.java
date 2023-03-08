package org.training.walletpayment.service;

import java.util.List;

import org.training.walletpayment.dto.ProductDto;

public interface ProductService {


	List<ProductDto> findByProductNameContaining(String productName, int page, int pagesize);
}
