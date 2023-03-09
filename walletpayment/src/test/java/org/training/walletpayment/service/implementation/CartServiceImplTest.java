package org.training.walletpayment.service.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.training.walletpayment.dto.ProductQuantityDto;
import org.training.walletpayment.dto.ResponseDto;
import org.training.walletpayment.entity.Cart;
import org.training.walletpayment.entity.Product;
import org.training.walletpayment.entity.User;
import org.training.walletpayment.exception.ProductNotFoundException;
import org.training.walletpayment.exception.UserNotFoundException;
import org.training.walletpayment.repository.CartRepository;
import org.training.walletpayment.service.ProductService;
import org.training.walletpayment.service.UserService;

@ExtendWith(SpringExtension.class)
class CartServiceImplTest {

	@InjectMocks
	private CartServiceImpl cartService;

	@Mock
	private UserService userService;

	@Mock
	private ProductService productService;

	@Mock
	private ProductQuantityServiceImpl productQuantityService;

	@Mock
	private CartRepository cartRepository;

	@Test
	void testSaveWithInvalidUser() {
		int userId = 1;
		List<ProductQuantityDto> productQuantityDtos = new ArrayList<>();

		Mockito.when(userService.findByUserId(userId)).thenReturn(Optional.empty());

		UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
			cartService.save(userId, productQuantityDtos);
		});

		assertEquals("User with user Id:1 does not exists", exception.getMessage());
	}

	@Test
	void testSaveWithInvalidProduct() {
		int userId = 1;
		List<ProductQuantityDto> productQuantityDtos = new ArrayList<>();
		ProductQuantityDto productQuantityDto = new ProductQuantityDto();
		productQuantityDto.setProductId(1);
		productQuantityDto.setQuantity(2);
		productQuantityDtos.add(productQuantityDto);

		User user = new User();
		user.setUserId(1);
		user.setFirstName("divya");
		user.setLastName("shree");
		user.setEmailId("divya@gmail.com");

		Mockito.when(userService.findByUserId(userId)).thenReturn(Optional.of(user));

		Mockito.when(productService.getAllProducts()).thenReturn(new ArrayList<>());

		ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () -> {
			cartService.save(userId, productQuantityDtos);
		});

		assertEquals("Product with product Id:1 does not exists", exception.getMessage());
	}

	@Test
	public void testSaveInvalidQuantity() {
		when(userService.findByUserId(1)).thenReturn(Optional.of(new User()));
		when(productService.getAllProducts()).thenReturn(Arrays.asList(new Product(1, "Product 1", 10.0)));
		ResponseDto response = cartService.save(1, Arrays.asList(new ProductQuantityDto(1, 0)));
		assertEquals(200, response.getResponseCode());
		assertEquals(Arrays.asList("Quantity should be at least 1"), response.getResponseMessage());
	}

	@Test
	public void testSaveValid() {
		User user = new User();
		user.setUserId(1);
		when(userService.findByUserId(1)).thenReturn(Optional.of(user));
		List<Product> products = Arrays.asList(new Product(1, "Product 1", 10.0));
		when(productService.getAllProducts()).thenReturn(products);
		Cart cart = new Cart();
		when(cartRepository.save(any(Cart.class))).thenReturn(cart);
		List<ProductQuantityDto> productQuantityDtos = Arrays.asList(new ProductQuantityDto(1, 1));
		cartService.save(1, productQuantityDtos);
		verify(cartRepository).save(any(Cart.class));
		verify(productQuantityService).saveAll(anyList());
	}

}
