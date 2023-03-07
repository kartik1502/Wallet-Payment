package org.training.walletpayment.exception;

public class NoSuchWalletExists extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final String message;

	public NoSuchWalletExists(String message) {
		super();
		this.message = message;
	}

	public NoSuchWalletExists() {
		super();
		this.message = "";
	}

	public String getMessage() {
		return message;
	}

}
