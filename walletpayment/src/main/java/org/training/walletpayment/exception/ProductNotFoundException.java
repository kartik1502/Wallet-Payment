package org.training.walletpayment.exception;

/**
 * Custom exception for handling cases where a product does not exist.
 */
public class ProductNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final  String message;

	/**
	 * 
	 * Constructs a new ProductNotFoundException exception with the given error message.
	 * 
	 * @param message the error message to be displayed
	 */
	public ProductNotFoundException(String message) {
		super(message);
		this.message = message;
	}

	/**
	 * 
	 * Constructs a new ProductNotFoundException exception with no error message.
	 */
	public ProductNotFoundException() {
		super();
		this.message = "";
	}

	/**
	 * 
	 * Returns the error message associated with this exception.
	 * 
	 * @return the error message
	 */
	@Override
	public String getMessage() {
		return message;
	}

}
