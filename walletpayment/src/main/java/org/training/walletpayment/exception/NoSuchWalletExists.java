package org.training.walletpayment.exception;

/**
 * Custom exception for handling cases where a wallet does not exist.
 */
public class NoSuchWalletExists extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final String errorMessage;

	/**
	 * 
	 * Constructs a new NoSuchWalletExists exception with the given error message.
	 * 
	 * @param message the error message to be displayed
	 */
	public NoSuchWalletExists(String message) {
		super(message);
		this.errorMessage = message;
	}

	/**
	 * 
	 * Constructs a new NoSuchWalletExists exception with no error message.
	 */
	public NoSuchWalletExists() {
		super();
		this.errorMessage = "";
	}

	/**
	 * 
	 * Returns the error message associated with this exception.
	 * 
	 * @return the error message
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

}
