package org.training.walletpayment.exception;

public class WalletExpired extends RuntimeException{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String  message;

    public WalletExpired() {
        this.message = "";
    }

    public WalletExpired(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
