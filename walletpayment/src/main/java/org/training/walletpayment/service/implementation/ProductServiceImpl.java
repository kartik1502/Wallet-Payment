package org.training.walletpayment.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.training.walletpayment.entity.Product;
import org.training.walletpayment.repository.ProductRepository;
@Service
public class ProductServiceImpl {
	
	public int pageNo;
	public int pageSize;

	@Service
	public class EmployeeService
	{
		@Autowired
	    ProductRepository productRepository;
		
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("productName"));

		Page<Product> pagedResult = productRepository.findAll(paging);
		

	    public List<Product> getAllProducts(Integer pageNo, Integer pageSize, String productName)
	    {
	        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(productName));

	        Page<Product> pagedResult = productRepository.findAll(paging);

	        if(pagedResult.hasContent()) {
	            return pagedResult.getContent();
	        } else {
	            return new ArrayList<Product>();
	        }
	    }
	}
}
