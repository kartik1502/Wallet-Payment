package org.training.walletpayment.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.training.walletpayment.dto.ProductDto;
import org.training.walletpayment.service.ProductService;

class ProductControllerTest {

	@Mock
	private ProductService productService;

	@InjectMocks
	private ProductController productController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetAll() {

		List<ProductDto> products = new ArrayList<>();
		ProductDto product1 = new ProductDto();
		product1.setProductName("Product1");
		products.add(product1);
		ProductDto product2 = new ProductDto();
		product2.setProductName("Product2");
		products.add(product2);

		when(productService.findByProductNameContaining("Product", 1, 10)).thenReturn(products);

		ResponseEntity<List<ProductDto>> response = productController.getALL("Product", 1, 10);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(products, response.getBody());
	}
}
