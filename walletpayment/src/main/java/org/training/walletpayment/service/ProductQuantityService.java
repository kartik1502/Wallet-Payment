package org.training.walletpayment.service;

import java.util.List;

import org.training.walletpayment.entity.Cart;
import org.training.walletpayment.entity.ProductQuantity;

/**
 * 
 * The ProductQuantityService interface provides methods to save and retrieve
 * ProductQuantity objects.
 */
public interface ProductQuantityService {

	/**
	 * 
	 * Saves a list of ProductQuantity objects.
	 * 
	 * @param productQuantities The list of ProductQuantity objects to save.
	 */
	void saveAll(List<ProductQuantity> productQuantities);

	/**
	 * 
	 * Retrieves a list of ProductQuantity objects for a given Cart.
	 * 
	 * @param cart The Cart to retrieve ProductQuantity objects for.
	 * @return A list of ProductQuantity objects for the given Cart.
	 */
	List<ProductQuantity> findByCart(Cart cart);

}
