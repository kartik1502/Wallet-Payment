package org.training.walletpayment.controller;

import java.util.List;

import javax.validation.constraints.Min;
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

/**
 * RestController class for handling HTTP requests related to products.
 */

@RestController
@RequestMapping("/")
public class ProductController {

	@Autowired
	private ProductService service;

	/**
	 * Retrieves a list of products containing the specified search string, within the specified pagination limits.
	 *
	 * @param productName The search string used to filter the retrieved products. The string must be at least 3 characters in length.
	 * @param page The index of the page to retrieve, starting from 0.
	 * @param pageSize The maximum number of products to retrieve per page.
	 * @return A ResponseEntity containing a List of ProductDto objects representing the retrieved products, along with an HTTP status code of 200 (OK) on success.
	 * @throws IllegalArgumentException If the specified search string is less than 3 characters in length.
	 */

	@GetMapping("/products")
	public ResponseEntity<List<ProductDto>> getALL(@RequestParam @Size(min = 3) String productName,
			@RequestParam @Min(0) int page, @RequestParam @Min(1) int pageSize) {

		return new ResponseEntity<>(service.findByProductNameContaining(productName, page, pageSize), HttpStatus.OK);

	}
}