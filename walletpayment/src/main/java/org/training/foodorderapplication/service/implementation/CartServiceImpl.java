package org.training.foodorderapplication.service.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.training.foodorderapplication.entity.Cart;
import org.training.foodorderapplication.repository.CartRepository;
import org.training.foodorderapplication.service.CartService;

public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository repository;

	@Override
	public Optional<Cart> findByCartId(int cartId) {
		
		return repository.findById(cartId);
		
	}

}
