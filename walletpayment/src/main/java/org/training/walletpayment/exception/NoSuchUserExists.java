package org.training.walletpayment.exception;

public class NoSuchUserExists extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final String message;

	public NoSuchUserExists(String message) {
		super();
		this.message = message;
	}

	public NoSuchUserExists() {
		super();
		this.message = "";
	}

	public String getMessage() {
		return message;
	}

}
