package org.training.walletpayment.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.training.walletpayment.entity.Cart;
import org.training.walletpayment.entity.ProductQuantity;
import org.training.walletpayment.repository.ProductQuantityRepository;
import org.training.walletpayment.service.ProductQuantityService;

@Service
public class ProductQuantityServiceImpl implements ProductQuantityService {

	@Autowired
	private ProductQuantityRepository repository;

	@Override
	public void saveAll(List<ProductQuantity> productQuantities) {
		repository.saveAll(productQuantities);
	}

	@Override
	public List<ProductQuantity> findByCart(Cart cart) {
		return repository.findAllByCart(cart);
	}

}
