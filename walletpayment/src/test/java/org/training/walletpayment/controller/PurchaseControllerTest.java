package org.training.walletpayment.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.training.walletpayment.dto.PurchaseDto;
import org.training.walletpayment.dto.ResponseDto;
import org.training.walletpayment.service.PurchaseService;

@ExtendWith(SpringExtension.class)
class PurchaseControllerTest {

	@InjectMocks
	private PurchaseController purchaseController;

	@Mock
	private PurchaseService purchaseService;

	@Test
	void testPurchaseSuccess() {
		int userId = 1;
		PurchaseDto purchaseDto = new PurchaseDto();
		purchaseDto.setCartId(1);
		purchaseDto.setWalletId(1000);
		ResponseDto responseDto = new ResponseDto();
		responseDto.setResponseCode(200l);
		when(purchaseService.purchase(userId, purchaseDto)).thenReturn(responseDto);

		ResponseEntity<ResponseDto> responseEntity = purchaseController.purchase(userId, purchaseDto);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

	}

}