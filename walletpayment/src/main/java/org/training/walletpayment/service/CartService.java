package org.training.walletpayment.service;

import java.util.Optional;

import org.training.walletpayment.entity.Cart;

public interface CartService {

	Optional<Cart> findByCartId(int cartId);

}
