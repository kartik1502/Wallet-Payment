package org.training.walletpayment.dto;

public class ProductDto {
private String productName;
	
	private double price;
	
	private int avaliableQuantity;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAvaliableQuantity() {
		return avaliableQuantity;
	}

	public void setAvaliableQuantity(int avaliableQuantity) {
		this.avaliableQuantity = avaliableQuantity;
	}
}