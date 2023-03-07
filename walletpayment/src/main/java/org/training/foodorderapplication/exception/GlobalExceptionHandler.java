package org.training.foodorderapplication.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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


	
}
