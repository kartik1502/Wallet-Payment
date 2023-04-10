package org.training.walletpayment.exception;

/**
 * Custom exception for handling cases where a quantity exceeded exception.
 */
public class QuantityExceededException extends RuntimeException{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String message;

	/**
	 * 
	 * Constructs a new QuantityExceededException exception with no error message.
	 */
    public QuantityExceededException() {
        this.message = "";
    }

    /**
	 * 
	 * Constructs a new QuantityExceededException exception with the given error message.
	 * 
	 * @param message the error message to be displayed
	 */
    public QuantityExceededException(String message1) {
        super();
        this.message = message1;
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
