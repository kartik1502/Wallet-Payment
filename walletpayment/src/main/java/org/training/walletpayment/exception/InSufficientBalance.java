package org.training.walletpayment.exception;

/**
Exception thrown when user has insufficient balance to make the purchase.
*/
public class InSufficientBalance extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final String message;

	/**
	Constructs a new {@code InSufficientBalance} instance with an empty message.
	*/
    public InSufficientBalance() {
        this.message = "";
    }

    
    /**
    Constructs a new {@code InSufficientBalance} instance with the specified message.
    @param message the detail message
    */
    public InSufficientBalance(String message) {
        super(message);
        this.message = message;
    }

    /**
    @return the detail message string of this {@code InSufficientBalance} instance.
    */
    @Override
    public String getMessage() {
        return message;
    }
}
