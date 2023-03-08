package org.training.walletpayment.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

	
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> errors = new ArrayList<>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			errors.add(error.getDefaultMessage());
		}
		ErrorResponse response = new ErrorResponse(403l, errors);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoSuchCartExists.class)
	public ResponseEntity<Object> handleNoSuchCartExistsException(NoSuchCartExists ex, WebRequest request){

		List<String> errorMessage = new ArrayList<>();
		errorMessage.add(ex.getLocalizedMessage());
		return new ResponseEntity<>(new ErrorResponse(404l, errorMessage), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoSuchUserExists.class)
	public ResponseEntity<Object> handleNoSuchUserExistsException(NoSuchUserExists ex, WebRequest request){
		List<String> errorMessage = new ArrayList<>();
		errorMessage.add(ex.getLocalizedMessage());
		return new ResponseEntity<>(new ErrorResponse(404l, errorMessage), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InSufficientBalance.class)
	public ResponseEntity<Object> handleInsufficientBalanceException(InSufficientBalance ex, WebRequest request){
		List<String> errorMessage = new ArrayList<>();
		errorMessage.add(ex.getLocalizedMessage());
		return new ResponseEntity<>(new ErrorResponse(404l, errorMessage), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoSuchWalletExists.class)
	public ResponseEntity<Object> handleNoSuchWalletExistsException(NoSuchWalletExists ex, WebRequest request){
		List<String> errorMessage = new ArrayList<>();
		errorMessage.add(ex.getLocalizedMessage());
		return new ResponseEntity<>(new ErrorResponse(404l, errorMessage), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(QuantityExceededException.class)
	public ResponseEntity<Object> handleQuantityExceededException(QuantityExceededException ex, WebRequest request){
		List<String> errorMessage = new ArrayList<>();
		errorMessage.add(ex.getLocalizedMessage());
		return new ResponseEntity<>(new ErrorResponse(404l, errorMessage), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(WalletExpired.class)
	public ResponseEntity<Object> handleWalletExpiredException(WalletExpired ex, WebRequest request){
		List<String> errorMessage = new ArrayList<>();
		errorMessage.add(ex.getLocalizedMessage());
		return new ResponseEntity<>(new ErrorResponse(404l, errorMessage), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<Object> handleNoSuchFoodExistsException(ProductNotFoundException ex, WebRequest req) {
		List<String> errors = new ArrayList<>();
		errors.add(ex.getLocalizedMessage());
		return new ResponseEntity<>(new ErrorResponse(404l, errors), HttpStatus.NOT_FOUND);
	}

}
