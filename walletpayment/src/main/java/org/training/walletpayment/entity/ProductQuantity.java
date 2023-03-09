package org.training.walletpayment.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductQuantity {

	public ProductQuantity(int productId, int quantity) {
		this.productId=productId;
		this.quantity=quantity;
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productQuantityId;
	
	private int productId;
	
	private int quantity;

	@ManyToOne
	@JoinColumn(name="cartId")
	private Cart cart;
	
	
}
