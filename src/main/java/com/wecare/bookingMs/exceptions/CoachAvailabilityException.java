package com.wecare.bookingMs.exceptions;

public class CoachAvailabilityException extends Exception {
	private static final long serialVersionUID = 1L;

	public CoachAvailabilityException() {
		super();
	}

	public CoachAvailabilityException(String errors) {
		super(errors);
	}
}