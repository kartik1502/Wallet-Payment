package org.training.walletpayment.service.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.training.walletpayment.entity.Cart;
import org.training.walletpayment.repository.CartRepository;

@Service
public class CartServiceImpl implements org.training.walletpayment.service.CartService {

	@Autowired
	private CartRepository repository;

	@Override
	public Optional<Cart> findByCartId(int cartId) {
		
		return repository.findById(cartId);
		
	}

}
