package org.training.foodorderapplication.service;

import java.util.Optional;

import org.training.foodorderapplication.entity.Cart;

public interface CartService {

	Optional<Cart> findByCartId(int cartId);

}
