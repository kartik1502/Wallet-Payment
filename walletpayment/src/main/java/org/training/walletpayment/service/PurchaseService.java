package org.training.walletpayment.service;

import javax.validation.Valid;

import org.training.walletpayment.dto.PurchaseDto;
import org.training.walletpayment.dto.ResponseDto;

public interface PurchaseService {

	ResponseDto purchase(int userId, @Valid PurchaseDto purchaseDto);

}
