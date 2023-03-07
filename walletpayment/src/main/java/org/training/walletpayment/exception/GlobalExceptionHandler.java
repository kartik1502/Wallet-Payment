package org.training.walletpayment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	
	@ExceptionHandler(value = UserNotFoundException.class)
	public ResponseEntity<Object> exception(UserNotFoundException exception) {
		return new ResponseEntity<>("No such User exists", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = ProductNotFoundException.class)
	public ResponseEntity<Object> exception(ProductNotFoundException exception) {
		return new ResponseEntity<>("No such product exists", HttpStatus.NOT_FOUND);
	}
	
}
