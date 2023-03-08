package org.training.walletpayment.exception;

public class NoSuchProductExist extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String message;

	public  NoSuchProductExist(String message) {
		super();
		this.message = message;
	}

	public  NoSuchProductExist() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


}
