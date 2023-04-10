package org.training.walletpayment.exception;

/**
 * Custom exception for handling cases where a cart does not exist.
 */
public class NoSuchCartExists extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final String errorMessage;

	/**
	 * 
	 * Constructs a new NoSuchCartExists exception with the given error message.
	 * 
	 * @param message the error message to be displayed
	 */
	public NoSuchCartExists(String message) {
		super(message);
		this.errorMessage = message;
	}

	/**
	 * 
	 * Constructs a new NoSuchCartExists exception with no error message.
	 */
	public NoSuchCartExists() {
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
