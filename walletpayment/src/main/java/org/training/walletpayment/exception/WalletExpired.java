package org.training.walletpayment.exception;

/**
 * Custom exception for handling cases where a wallet expired.
 */
public class WalletExpired extends RuntimeException{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String  message;

	/**
	 * 
	 * Constructs a new WalletExpired exception with no error message.
	 */
    public WalletExpired() {
        this.message = "";
    }

    /**
	 * 
	 * Constructs a new WalletExpired exception with the given error message.
	 * 
	 * @param message the error message to be displayed
	 */
    public WalletExpired(String message) {
        super(message);
        this.message = message;
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
