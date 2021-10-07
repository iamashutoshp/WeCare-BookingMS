package com.wecare.bookingMs.exceptions;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.wecare.bookingMs.responseModel.CustomResponse;

@RestControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception ex) {
		return ex.getMessage();
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	private ResponseEntity<CustomResponse> handleEntityNotFound(EntityNotFoundException ex) {
		CustomResponse error = new CustomResponse(HttpStatus.NOT_FOUND, "Entity not found", ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
}