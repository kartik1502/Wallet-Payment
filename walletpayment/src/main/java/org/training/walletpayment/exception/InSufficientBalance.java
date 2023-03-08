package org.training.walletpayment.exception;

public class InSufficientBalance extends RuntimeException {

    private final String message;

    public InSufficientBalance() {
        this.message = "";
    }

    public InSufficientBalance(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
