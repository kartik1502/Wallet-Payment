package org.training.walletpayment.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.training.walletpayment.dto.PurchaseDto;
import org.training.walletpayment.dto.ResponseDto;
import org.training.walletpayment.service.PurchaseService;

import io.swagger.v3.oas.annotations.Operation;

/**
 * RestController class for handling HTTP requests related to purchases.
 */
@RestController
@RequestMapping
public class PurchaseController {

	@Autowired
	private PurchaseService service;

	/**
	 * Handles a purchase request for the specified user, using the provided purchase information.
	 *
	 * @param userId The ID of the user making the purchase, provided in the request header.
	 * @param purchaseDto A PurchaseDto object representing the purchase information, provided in the request body.
	 * @return A ResponseEntity containing a ResponseDto object that represents the result of the purchase operation, along with an HTTP status code of 200 (OK) on success.
	 * @throws InterruptedException 
	 */

	@Operation(summary = "Making a purchase")
	@PostMapping("/purchases")
	public ResponseEntity<ResponseDto> purchase(@RequestHeader int userId,@Valid @RequestBody PurchaseDto purchaseDto) throws InterruptedException{
		return new ResponseEntity<>(service.purchase(userId ,purchaseDto), HttpStatus.OK);
	}


}
