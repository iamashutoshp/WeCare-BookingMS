package com.wecare.bookingMs.exceptions;

public class BookingException extends Exception {
	private static final long serialVersionUID = 1L;

	public BookingException() {
		super();
	}

	public BookingException(String errors) {
		super(errors);
	}
}
