package org.training.walletpayment.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.training.walletpayment.entity.Product;
import org.training.walletpayment.repository.ProductRepository;
import org.training.walletpayment.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	public  List<Product> getAllProducts()
	{
		return productRepository.findAll();
	}
	
}
