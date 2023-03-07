package org.training.walletpayment.service.implementation;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.training.walletpayment.dto.PurchaseDto;
import org.training.walletpayment.dto.ResponseDto;
import org.training.walletpayment.entity.Cart;
import org.training.walletpayment.entity.Purchase;
import org.training.walletpayment.entity.User;
import org.training.walletpayment.exception.NoSuchCartExists;
import org.training.walletpayment.exception.NoSuchUserExists;
import org.training.walletpayment.exception.NoSuchWalletExists;
import org.training.walletpayment.service.CartService;
import org.training.walletpayment.service.PurchaseService;
import org.training.walletpayment.service.UserService;
import org.training.walletpayment.service.WalletService;

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
