package org.training.walletpayment.service.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.training.walletpayment.entity.Product;
import org.training.walletpayment.repository.ProductRepository;

@ExtendWith(SpringExtension.class)
class ProductServiceImplTest {
	
	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductServiceImpl productService;

	
	
	@Test
	void testGetAllProducts() {
		List<Product> productList = new ArrayList<>();
		Product product = new Product();
		product.setProductId(1);
		product.setProductName("soap");
		product.setPrice(10.0);
		product.setAvaliableQuantity(2);
		productList.add(product);

		when(productRepository.findAll()).thenReturn(productList);

		List<Product> result = productService.getAllProducts();

		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(productList, result);
	}

}
