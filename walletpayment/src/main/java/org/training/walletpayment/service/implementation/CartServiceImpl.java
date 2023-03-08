package org.training.walletpayment.service.implementation;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.training.walletpayment.entity.Cart;
import org.training.walletpayment.entity.User;
import org.training.walletpayment.repository.CartRepository;
import org.training.walletpayment.service.CartService;
import org.training.walletpayment.service.ProductService;
import org.training.walletpayment.service.UserService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository repository;
	
	@Autowired
	private UserService userService;

	@Autowired
	private ProductService productService;

	@Autowired
	private CartRepository cartRepository;

	Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

	@Override
	public Optional<Cart> findCartByCartIdAndAndUser(int cartId, User user) {
		return repository.findCartByCartIdAndAndUser(cartId, user);
	}

}
