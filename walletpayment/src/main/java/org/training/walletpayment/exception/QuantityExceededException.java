package org.training.walletpayment.exception;

public class QuantityExceededException extends RuntimeException{

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
