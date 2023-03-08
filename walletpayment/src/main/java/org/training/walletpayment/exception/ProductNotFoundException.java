package org.training.walletpayment.exception;

public class ProductNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final  String message;

	public ProductNotFoundException(String message) {
		super(message);
		this.message = message;
	}

	public ProductNotFoundException() {
		super();
		this.message = "";
	}

	@Override
	public String getMessage() {
		return message;
	}

}
