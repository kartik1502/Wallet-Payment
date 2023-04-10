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

/**
 * Controller advice class that handles exceptions for all controllers in the application.
 * Extends ResponseEntityExceptionHandler to provide default handling for common Spring boot exceptions.
 */

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Handles MethodArgumentNotValidExceptions, which occur when a request's method argument fails validation.
	 *
	 * @param ex The exception object that was thrown.
	 * @param headers The HTTP headers for the response.
	 * @param status The HTTP status code for the response.
	 * @param request The current request.
	 * @return A ResponseEntity containing an ErrorResponse object that represents the error message and an HTTP status code of 400 (Bad Request).
	 */
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

	/**
	 * Handles NoSuchCartExists exceptions, which occur when a requested cart does not exist in the system.
	 *
	 * @param ex The exception object that was thrown.
	 * @param request The current request.
	 * @return A ResponseEntity containing an ErrorResponse object that represents the error message and an HTTP status code of 404 (Not Found).
	 */
	@ExceptionHandler(NoSuchCartExists.class)
	public ResponseEntity<Object> handleNoSuchCartExistsException(NoSuchCartExists ex, WebRequest request){

		List<String> errorMessage = new ArrayList<>();
		errorMessage.add(ex.getLocalizedMessage());
		return new ResponseEntity<>(new ErrorResponse(404l, errorMessage), HttpStatus.NOT_FOUND);
	}

	/**
	 * Handles NoSuchUserExists exceptions, which occur when a requested user does not exist in the system.
	 *
	 * @param ex The exception object that was thrown.
	 * @param request The current request.
	 * @return A ResponseEntity containing an ErrorResponse object that represents the error message and an HTTP status code of 404 (Not Found).
	 */
	@ExceptionHandler(NoSuchUserExists.class)
	public ResponseEntity<Object> handleNoSuchUserExistsException(NoSuchUserExists ex, WebRequest request){
		List<String> errorMessage = new ArrayList<>();
		errorMessage.add(ex.getLocalizedMessage());
		return new ResponseEntity<>(new ErrorResponse(404l, errorMessage), HttpStatus.NOT_FOUND);
	}

	/**
	 * Handles InsufficientBalance exceptions, which occur when a user's wallet balance is insufficient for a requested transaction.
	 *
	 * @param ex The exception object that was thrown.
	 * @param request The current request.
	 * @return A ResponseEntity containing an ErrorResponse object that represents the error message and an HTTP status code of 404 (Not Found).
	 */
	@ExceptionHandler(InSufficientBalance.class)
	public ResponseEntity<Object> handleInsufficientBalanceException(InSufficientBalance ex, WebRequest request){
		List<String> errorMessage = new ArrayList<>();
		errorMessage.add(ex.getLocalizedMessage());
		return new ResponseEntity<>(new ErrorResponse(404l, errorMessage), HttpStatus.NOT_FOUND);
	}
	
	/**
	 * Handles NoSuchWalletExists exceptions, which occur when a requested user wallet does not exist in the system.
	 *
	 * @param ex The exception object that was thrown.
	 * @param request The current request.
	 * @return A ResponseEntity containing an ErrorResponse object that represents the error message and an HTTP status code of 404 (Not Found).
	 */
	@ExceptionHandler(NoSuchWalletExists.class)
	public ResponseEntity<Object> handleNoSuchWalletExistsException(NoSuchWalletExists ex, WebRequest request){
		List<String> errorMessage = new ArrayList<>();
		errorMessage.add(ex.getLocalizedMessage());
		return new ResponseEntity<>(new ErrorResponse(404l, errorMessage), HttpStatus.NOT_FOUND);
	}

	/**
	 * Handles QuantityExceededExceptions, which occur when a requested product quantity exceeds the available inventory.
	 *
	 * @param ex The exception object that was thrown.
	 * @param request The current request.
	 * @return A ResponseEntity containing an ErrorResponse object that represents the error message and an HTTP status code of 404 (Not Found).
	 */
	@ExceptionHandler(QuantityExceededException.class)
	public ResponseEntity<Object> handleQuantityExceededException(QuantityExceededException ex, WebRequest request){
		List<String> errorMessage = new ArrayList<>();
		errorMessage.add(ex.getLocalizedMessage());
		return new ResponseEntity<>(new ErrorResponse(404l, errorMessage), HttpStatus.NOT_FOUND);
	}

	/**
	 * Handles WalletExpired exceptions, which occur when a user's wallet has expired.
	 *
	 * @param ex The exception object that was thrown.
	 * @param request The current request.
	 * @return A ResponseEntity containing an ErrorResponse object that represents the error message and an HTTP status code of 404 (Not Found).
	 */
	@ExceptionHandler(WalletExpired.class)
	public ResponseEntity<Object> handleWalletExpiredException(WalletExpired ex, WebRequest request){
		List<String> errorMessage = new ArrayList<>();
		errorMessage.add(ex.getLocalizedMessage());
		return new ResponseEntity<>(new ErrorResponse(404l, errorMessage), HttpStatus.NOT_FOUND);
	}

	/**
	 * Handles ProductNotFoundExceptions, which occur when a requested product does not exist in the system.
	 *
	 * @param ex The exception object that was thrown.
	 * @param request The current request.
	 * @return A ResponseEntity containing an ErrorResponse object that represents the error message and an HTTP status code of 404 (Not Found).
	 */
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException ex, WebRequest request){
		List<String> errorMessage = new ArrayList<>();
		errorMessage.add(ex.getLocalizedMessage());
		return new ResponseEntity<>(new ErrorResponse(404l, errorMessage), HttpStatus.NOT_FOUND);
	}
}
