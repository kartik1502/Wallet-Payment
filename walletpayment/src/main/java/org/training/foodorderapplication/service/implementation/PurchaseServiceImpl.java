package org.training.foodorderapplication.service.implementation;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.training.foodorderapplication.dto.PurchaseDto;
import org.training.foodorderapplication.dto.ResponseDto;
import org.training.foodorderapplication.entity.Cart;
import org.training.foodorderapplication.entity.Purchase;
import org.training.foodorderapplication.entity.User;
import org.training.foodorderapplication.exception.NoSuchCartExists;
import org.training.foodorderapplication.exception.NoSuchUserExists;
import org.training.foodorderapplication.exception.NoSuchWalletExists;
import org.training.foodorderapplication.service.CartService;
import org.training.foodorderapplication.service.PurchaseService;
import org.training.foodorderapplication.service.UserService;
import org.training.foodorderapplication.service.WalletService;

@Service
public class PurchaseServiceImpl implements PurchaseService{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private WalletService walletService;

	@Override
	public ResponseDto purchase(int userId, @Valid PurchaseDto purchaseDto) {
		
		User user = userService.findByUserId(userId).orElseThrow(
				() -> new NoSuchUserExists("User with user Id:"+userId+" does not exists"));
		
		Cart cart = cartService.findByCartId(purchaseDto.getCartId()).orElseThrow(
				() -> new NoSuchCartExists("Cart with cart Id:"+purchaseDto.getCartId()+" dose not exists"));
		
		walletService.findByWalletId(purchaseDto.getWalletId()).orElseThrow(
				() -> new NoSuchWalletExists("Wallet with wallet Id:"+purchaseDto.getWalletId()+"does not exists"));
		Purchase purchase = new Purchase();
		purchase.setUser(user);
		purchase.setWalletId(purchaseDto.getWalletId());
		
		return null;
	}

}
