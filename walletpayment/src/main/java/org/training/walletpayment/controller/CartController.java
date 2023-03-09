package org.training.walletpayment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.training.walletpayment.dto.ProductQuantityDto;
import org.training.walletpayment.dto.ResponseDto;
import org.training.walletpayment.service.CartService;

@RestController
@RequestMapping("/")
public class CartController {

	@Autowired
	private CartService cartService;

	@PostMapping("/users/{userId}/carts")
	public ResponseEntity<ResponseDto> addCart(@PathVariable int userId,
			@RequestBody List<ProductQuantityDto> productquantitydto) {
		return new ResponseEntity<>(cartService.save(userId, productquantitydto), HttpStatus.CREATED);
	}

}
