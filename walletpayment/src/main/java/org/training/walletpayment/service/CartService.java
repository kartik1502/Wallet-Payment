package org.training.walletpayment.service;

import java.util.List;

import org.training.walletpayment.dto.ProductQuantityDto;
import org.training.walletpayment.dto.ResponseDto;

public interface CartService {

	ResponseDto save(int userId,List<ProductQuantityDto> productquantitydto);

}
