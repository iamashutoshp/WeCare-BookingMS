package com.wecare.bookingMs.responseModel;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;

public class CustomResponse {
	private HttpStatus httpStatus;

	private LocalDateTime timestamp;

	private String message;

	private Object result;
	
	public CustomResponse() {
		super();
	}
	
	public CustomResponse(HttpStatus httpStatus, String message, Object result) {
		this.httpStatus = httpStatus;
		this.timestamp = LocalDateTime.now();
		this.message = message;
		this.result = result;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

}
