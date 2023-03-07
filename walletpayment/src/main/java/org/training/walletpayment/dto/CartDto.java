package org.training.walletpayment.dto;

import java.util.List;
import javax.persistence.OneToMany;
import lombok.Data;

@Data

public class CartDto {

	private int userId;

	private double totalPrice;

	@OneToMany
	private List<ProductQuantityDto> productQuantitiesdto;

}
