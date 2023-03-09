package org.training.walletpayment.service;

import java.util.List;

import org.training.walletpayment.entity.ProductQuantity;

public interface ProductQuantityService {

	void saveAll(List<ProductQuantity> productQuantities);

}
