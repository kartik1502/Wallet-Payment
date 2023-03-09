package org.training.walletpayment.service.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
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
import org.training.walletpayment.exception.NoSuchUserExists;
import org.training.walletpayment.exception.ProductNotFoundException;
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
	private CartRepository cartRepository;

	@Test
	void testSaveWithValidData() {
		int userId = 1;
		List<ProductQuantityDto> productQuantityDtos = new ArrayList<>();
		ProductQuantityDto productQuantityDto=new ProductQuantityDto();
		productQuantityDto.setProductId(1);
		productQuantityDto.setQuantity(2);
		productQuantityDtos.add(productQuantityDto);

		User user = new User();
		user.setUserId(1);
		user.setFirstName("divya");
		user.setLastName("shree");
		user.setEmailId("divya@gmail.com");
		Mockito.when(userService.findByUserId(userId)).thenReturn(Optional.of(user));

		List<Product> products = new ArrayList<>();
		Product product=new Product();
		product.setProductId(1);
		product.setProductName("soap");
		product.setPrice(10.0);
		product.setAvaliableQuantity(2);
		products.add(product);
		Mockito.when(productService.getAllProducts()).thenReturn(products);

		Mockito.when(cartRepository.save(Mockito.any(Cart.class))).thenReturn(new Cart());

		ResponseDto responseDto = cartService.save(userId, productQuantityDtos);

		assertEquals(201l, responseDto.getReaponseCode());
		assertEquals("Cart saved successfully", responseDto.getResponseMessage().get(0));
	}

	@Test
	void testSaveWithInvalidUser() {
		int userId = 1;
		List<ProductQuantityDto> productQuantityDtos = new ArrayList<>();

		Mockito.when(userService.findByUserId(userId)).thenReturn(Optional.empty());

		NoSuchUserExists exception = assertThrows(NoSuchUserExists.class, () -> {
			cartService.save(userId, productQuantityDtos);
		});

		assertEquals("User with user Id:1 does not exists", exception.getMessage());
	}

	@Test
	void testSaveWithInvalidProduct() {
		int userId = 1;
		List<ProductQuantityDto> productQuantityDtos = new ArrayList<>();
		ProductQuantityDto productQuantityDto=new ProductQuantityDto();
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

}








