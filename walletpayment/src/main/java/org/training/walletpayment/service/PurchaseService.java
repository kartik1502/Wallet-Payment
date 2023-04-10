package org.training.walletpayment.service;

import javax.validation.Valid;

import org.training.walletpayment.dto.PurchaseDto;
import org.training.walletpayment.dto.ResponseDto;

/**
 * Service interface for purchases.
 */
public interface PurchaseService {

	/**
	 * Makes a purchase for the given user with the specified purchase details.
	 *
	 * @param userId      The ID of the user making the purchase.
	 * @param purchaseDto The purchase details.
	 * @return A ResponseDto object containing the result of the purchase.
	 * @throws InterruptedException 
	 */
	ResponseDto purchase(int userId, @Valid PurchaseDto purchaseDto) throws InterruptedException;

}
