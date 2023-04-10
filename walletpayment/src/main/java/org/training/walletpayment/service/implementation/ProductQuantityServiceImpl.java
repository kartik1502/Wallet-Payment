package org.training.walletpayment.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.training.walletpayment.entity.Cart;

import org.training.walletpayment.entity.ProductQuantity;
import org.training.walletpayment.repository.ProductQuantityRepository;
import org.training.walletpayment.service.ProductQuantityService;

/**
 * 
 * Implementation of the {@link ProductQuantityService} interface.
 * 
 * Uses {@link ProductQuantityRepository} to persist {@link ProductQuantity}
 * entities.
 */
@Service
public class ProductQuantityServiceImpl implements ProductQuantityService {

	@Autowired
	private ProductQuantityRepository repository;

	/**
	 * 
	 * Saves a list of {@link ProductQuantity} entities to the repository.
	 * 
	 * @param productQuantities the list of {@link ProductQuantity} entities to save
	 */
	@Override
	public void saveAll(List<ProductQuantity> productQuantities) {
		repository.saveAll(productQuantities);
	}

	/**
	 * 
	 * Retrieves a list of {@link ProductQuantity} entities associated with the
	 * given {@link Cart}.
	 * 
	 * @param cart the {@link Cart} to retrieve the associated
	 *             {@link ProductQuantity} entities for
	 * @return the list of associated {@link ProductQuantity} entities
	 */
	@Override
	public List<ProductQuantity> findByCart(Cart cart) {

		return repository.findAllByCart(cart);
	}

}
