package org.training.walletpayment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.training.walletpayment.entity.Cart;
import org.training.walletpayment.entity.ProductQuantity;

/**
 * 
 * This interface extends JpaRepository to handle database operations for
 * ProductQuantity entity.
 */
public interface ProductQuantityRepository extends JpaRepository<ProductQuantity, Integer> {

	/**
	 * 
	 * Retrieves all the product quantities associated with the given Cart entity.
	 * 
	 * @param cart the Cart entity whose ProductQuantity entities need to be
	 *             retrieved.
	 * @return a list of ProductQuantity entities associated with the given Cart
	 *         entity.
	 */
	List<ProductQuantity> findAllByCart(Cart cart);

}
