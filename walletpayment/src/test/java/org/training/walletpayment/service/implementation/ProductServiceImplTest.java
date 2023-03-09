package org.training.walletpayment.service.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.training.walletpayment.dto.ProductDto;
import org.training.walletpayment.entity.Product;
import org.training.walletpayment.exception.ProductNotFoundException;
import org.training.walletpayment.repository.ProductRepository;

@ExtendWith(SpringExtension.class)
class ProductServiceImplTest {
	
	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductServiceImpl productService;

	@Test
	void testFindByProductNameContaining() {
		String productName = "Apple";
		int page = 0;
		int pagesize = 10;

		List<Product> productList = new ArrayList<>();
		Product product = new Product();
		product.setProductId(1);
		product.setProductName("Apple");
		productList.add(product);

		Pageable pageable = PageRequest.of(page, pagesize);
		when(productRepository.findByProductNameContainingIgnoreCase(productName, pageable)).thenReturn(productList);

		List<ProductDto> productDtoList = productService.findByProductNameContaining(productName, page, pagesize);

		assertEquals(1, productDtoList.size());
		assertEquals("Apple", productDtoList.get(0).getProductName());
	}

	@Test
	void testFindByProductNameContainingNoResults() {
		String productName = "Banana";
		int page = 0;
		int pagesize = 10;

		Pageable pageable = PageRequest.of(page, pagesize);
		when(productRepository.findByProductNameContainingIgnoreCase(productName, pageable))
				.thenReturn(new ArrayList<>());

		ProductNotFoundException exception = org.junit.jupiter.api.Assertions.assertThrows(
				ProductNotFoundException.class,
				() -> productService.findByProductNameContaining(productName, page, pagesize));
		assertEquals("Product with product name doesn't exists", exception.getMessage());
	}
}
