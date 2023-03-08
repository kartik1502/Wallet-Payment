package org.training.walletpayment.exception;

public class QuantityExceededException extends RuntimeException{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String message;

    public QuantityExceededException() {
        this.message = "";
    }

    public QuantityExceededException(String message1) {
        super();
        this.message = message1;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
