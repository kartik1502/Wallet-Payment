package org.training.walletpayment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.training.walletpayment.entity.Cart;
import org.training.walletpayment.entity.User;

import java.util.Optional;

/**
 * 
 * Repository interface for managing cart entities in the database.
 */
public interface CartRepository extends JpaRepository<Cart, Integer> {

	/**
	 * 
	 * Finds the cart with the given cart ID and user.
	 * 
	 * @param cartId the ID of the cart to find
	 * @param user   the user who owns the cart
	 * @return an Optional containing the cart, or an empty Optional if the cart is
	 *         not found
	 */
	Optional<Cart> findCartByCartIdAndAndUser(int cartId, User user);
}
