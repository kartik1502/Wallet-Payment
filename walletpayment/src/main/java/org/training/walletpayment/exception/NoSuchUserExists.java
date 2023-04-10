package org.training.walletpayment.exception;

/**
 * Custom exception for handling cases where a user does not exist.
 */
public class NoSuchUserExists extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final String errorMessage;

	/**
	 * 
	 * Constructs a new NoSuchUserExists exception with the given error message.
	 * 
	 * @param message the error message to be displayed
	 */
	public NoSuchUserExists(String message) {
		super(message);
		this.errorMessage = message;
	}

	/**
	 * 
	 * Constructs a new NoSuchUserExists exception with no error message.
	 */
	public NoSuchUserExists() {
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
