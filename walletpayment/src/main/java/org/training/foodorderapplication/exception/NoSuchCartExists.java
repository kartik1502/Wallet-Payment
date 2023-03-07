package org.training.foodorderapplication.exception;

public class NoSuchCartExists extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final String message;

	public NoSuchCartExists(String message) {
		super(message);
		this.message = message;
	}

	public NoSuchCartExists() {
		super();
		this.message = "";
	}

	public String getMessage() {
		return message;
	}

}
