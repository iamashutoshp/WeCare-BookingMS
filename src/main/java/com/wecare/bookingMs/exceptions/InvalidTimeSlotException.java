package com.wecare.bookingMs.exceptions;

public class InvalidTimeSlotException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidTimeSlotException() {
		super();
	}

	public InvalidTimeSlotException(String errors) {
		super(errors);
	}
}
