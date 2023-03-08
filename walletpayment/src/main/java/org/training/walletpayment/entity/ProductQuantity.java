package org.training.walletpayment.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductQuantity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productQuantityId;
	
	private int productId;
	
	private int quantity;

	public ProductQuantity(int productId, int quantity) {
		super();
		this.productId = productId;
		this.quantity = quantity;
	}
	
	
}
