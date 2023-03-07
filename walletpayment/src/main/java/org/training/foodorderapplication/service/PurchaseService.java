package org.training.foodorderapplication.service;

import javax.validation.Valid;

import org.training.foodorderapplication.dto.PurchaseDto;
import org.training.foodorderapplication.dto.ResponseDto;

public interface PurchaseService {

	ResponseDto purchase(int userId, @Valid PurchaseDto purchaseDto);

}
