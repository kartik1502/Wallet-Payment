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

@RestController
@RequestMapping
public class PurchaseController {

	@Autowired
	private PurchaseService service;

	
	@PostMapping("/purchases")
	public ResponseEntity<ResponseDto> purchase(@RequestHeader int userId,@Valid @RequestBody PurchaseDto purchaseDto){
		return new ResponseEntity<ResponseDto>(service.purchase(userId ,purchaseDto), HttpStatus.OK);
	}


}
