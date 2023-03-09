package org.training.walletpayment.controller;
import java.util.List;

import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.training.walletpayment.dto.ProductDto;
import org.training.walletpayment.service.ProductService;


@RestController
@RequestMapping("/")
public class ProductController {
	
	@Autowired
    private ProductService service;
	
	
	@GetMapping("/products")
	public ResponseEntity<List<ProductDto>> getALL(@RequestParam @Size(min=3) String productName,@RequestParam int page, @RequestParam int pageSize){
		
		return new ResponseEntity<>(service.findByProductNameContaining(productName, page, pageSize), HttpStatus.OK);

	}
}