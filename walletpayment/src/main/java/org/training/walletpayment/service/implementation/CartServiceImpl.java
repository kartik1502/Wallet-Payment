package org.training.walletpayment.service.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.training.walletpayment.entity.Cart;
import org.training.walletpayment.entity.User;
import org.training.walletpayment.repository.CartRepository;
import org.training.walletpayment.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository repository;

	@Override
	public Optional<Cart> findCartByCartIdAndAndUser(int cartId, User user) {
		return repository.findCartByCartIdAndAndUser(cartId, user);
	}

}
