package org.training.walletpayment.service.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.training.walletpayment.entity.Cart;
import org.training.walletpayment.entity.ProductQuantity;
import org.training.walletpayment.repository.ProductQuantityRepository;

class ProductQuantityServiceImplTest {

	@Mock
	private ProductQuantityRepository productQuantityRepository;

	@InjectMocks
	private ProductQuantityServiceImpl productQuantityService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testSaveAll() {

		List<ProductQuantity> productQuantities = new ArrayList<>();
		ProductQuantity productQuantity1 = new ProductQuantity();
		productQuantity1.setProductQuantityId(1);
		productQuantity1.setQuantity(2);
		productQuantities.add(productQuantity1);
		ProductQuantity productQuantity2 = new ProductQuantity();
		productQuantity2.setProductQuantityId(2);
		productQuantity2.setQuantity(3);
		productQuantities.add(productQuantity2);

		productQuantityService.saveAll(productQuantities);

		verify(productQuantityRepository).saveAll(productQuantities);
	}

	@Test
	void testFindByCart() {

		Cart cart = new Cart();
		cart.setCartId(1);

		List<ProductQuantity> productQuantities = new ArrayList<>();
		ProductQuantity productQuantity1 = new ProductQuantity();
		productQuantity1.setProductQuantityId(1);
		productQuantity1.setQuantity(2);
		productQuantity1.setCart(cart);
		productQuantities.add(productQuantity1);
		ProductQuantity productQuantity2 = new ProductQuantity();
		productQuantity2.setProductQuantityId(2);
		productQuantity2.setQuantity(3);
		productQuantity2.setCart(cart);
		productQuantities.add(productQuantity2);

		when(productQuantityRepository.findAllByCart(cart)).thenReturn(productQuantities);

		List<ProductQuantity> result = productQuantityService.findByCart(cart);

		verify(productQuantityRepository).findAllByCart(cart);

		assertEquals(productQuantities, result);
	}
}