package org.training.walletpayment.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.training.walletpayment.dto.ProductQuantityDto;
import org.training.walletpayment.dto.ResponseDto;
import org.training.walletpayment.service.CartService;

@ExtendWith(SpringExtension.class)
class CartControllerTest {

	@InjectMocks
	private CartController cartController;

	@Mock
	private CartService cartService;

	@Test
	void testAddCart() {
		int userId = 1;
		List<ProductQuantityDto> productQuantityDtos = new ArrayList<>();
		ProductQuantityDto productQuantityDto = new ProductQuantityDto();
		productQuantityDto.setProductId(1);
		productQuantityDto.setQuantity(5);
		productQuantityDtos.add(productQuantityDto);

		ResponseDto responseDto = new ResponseDto();
		responseDto.setMessage("Cart added successfully");

		when(cartService.save(userId, productQuantityDtos)).thenReturn(responseDto);

		ResponseEntity<ResponseDto> responseEntity = cartController.addCart(userId, productQuantityDtos);

		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		assertEquals("Cart added successfully", responseEntity.getBody().getMessage());
	}

}
