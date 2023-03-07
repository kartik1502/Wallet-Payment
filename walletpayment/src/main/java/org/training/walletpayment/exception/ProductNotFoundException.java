package org.training.walletpayment.exception;

public class ProductNotFoundException  extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private String message;

	public ProductNotFoundException(String message) {
		super(message);
	}

	public ProductNotFoundException()  {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
