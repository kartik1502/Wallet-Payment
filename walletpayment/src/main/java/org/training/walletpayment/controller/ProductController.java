package org.training.walletpayment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.training.walletpayment.entity.Product;
import org.training.walletpayment.service.ProductService;

public class ProductController {
	@Autowired
    ProductService service;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(
                        @RequestParam(defaultValue = "1") Integer pageNo,
                        @RequestParam(defaultValue = "10") Integer pageSize,
                        @RequestParam(defaultValue = "productName") String productName)
    {
        List<Product> list = service.getAllProducts(pageNo, pageSize, productName);

        return new ResponseEntity<List<Product>>(list, new HttpHeaders(), HttpStatus.OK);
    }
}
