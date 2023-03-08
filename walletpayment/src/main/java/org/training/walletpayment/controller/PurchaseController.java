package org.training.walletpayment.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.training.walletpayment.dto.PurchaseDto;
import org.training.walletpayment.dto.ResponseDto;
import org.training.walletpayment.entity.Product;
import org.training.walletpayment.entity.Wallet;
import org.training.walletpayment.repository.ProductRepository;
import org.training.walletpayment.repository.WalletRepository;
import org.training.walletpayment.service.PurchaseService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping
public class PurchaseController {

	@Autowired
	private PurchaseService service;

	@Autowired
	private ProductRepository repository;
	
	@PostMapping("/purchases")
	public ResponseEntity<ResponseDto> purchase(@RequestHeader int userId,@Valid @RequestBody PurchaseDto purchaseDto){
		return new ResponseEntity<ResponseDto>(service.purchase(userId ,purchaseDto), HttpStatus.OK);
	}


}
