package org.training.walletpayment.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.training.walletpayment.dto.ProductDto;
import org.training.walletpayment.entity.Product;
import org.training.walletpayment.exception.ProductNotFoundException;
import org.training.walletpayment.repository.ProductRepository;
import org.training.walletpayment.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;
    
    Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Override
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    @Override
    public void saveAll(List<Product> productList) {
        repository.saveAll(productList);
    }

    @Override
	public List<ProductDto> findByProductNameContaining(String productName, int page, int pagesize) {
		
		List<ProductDto> dtos = new ArrayList<>();
		Pageable pageable = PageRequest.of(page, pagesize);
		repository.findByProductNameContainingIgnoreCase(productName,pageable).forEach(p ->{
			ProductDto dto = new ProductDto();
			BeanUtils.copyProperties(p, dto ,"productId");
			dtos.add(dto);
		});
		
		if(repository.findByProductNameContainingIgnoreCase(productName).isEmpty()) {
			logger.error("No such Product exists exception handled");
			throw new ProductNotFoundException("Product with product name doesn't exists");
		}
		if (dtos.isEmpty()) {
			logger.error("No such Product exists exception handled");
			throw new ProductNotFoundException("No Such Product found in this page");
		}
		return dtos;
	}
	
}

