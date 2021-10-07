package com.wecare.bookingMs.controller;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.wecare.bookingMs.dto.BookingDTO;
import com.wecare.bookingMs.exceptions.CoachAvailabilityException;
import com.wecare.bookingMs.exceptions.InvalidTimeSlotException;
import com.wecare.bookingMs.responseModel.CustomResponse;
import com.wecare.bookingMs.service.BookingService;

@RestController
@RequestMapping("/users")
@Validated
@CrossOrigin
public class UserBookingController {

	@Autowired
	private BookingService bookingService;

	private Logger log = LoggerFactory.getLogger(UserBookingController.class);

	@PostMapping(consumes = "application/json", value = "/{userId}/booking/{coachId}")
	@HystrixCommand(fallbackMethod = "bookAppointmentFallBack")
	public CustomResponse bookAppointment(@PathVariable("userId") String userId,
			@PathVariable("coachId") String coachId, @RequestBody BookingDTO bookingDTO) {
		CustomResponse response = new CustomResponse();

		String slot = bookingDTO.getSlot();
		LocalDate dateOfAppointment = bookingDTO.getAppointmentDate();
		log.info("Inside bookAppointment : userId : " + userId + " | " + " coachId : " + coachId + " | slot : " + slot
				+ " | dateOfAppointment : " + dateOfAppointment);
		log.info(bookingDTO.toString());
		Boolean res = false;
		try {
			res = bookingService.bookAppointment(userId, coachId, slot, dateOfAppointment);
			if (res) {
				response.setHttpStatus(HttpStatus.OK);
				response.setMessage("Appointment Booked");
				response.setResult(res);
			}
		} catch (CoachAvailabilityException | InvalidTimeSlotException e) {
			log.error("error in Booking appointment : "+e.getMessage());
			e.printStackTrace();
			response.setHttpStatus(HttpStatus.NOT_FOUND);
			response.setMessage("Error in Booking appointment : "+e.getMessage());
			response.setResult(false);
		}
		return response;
	}

	public ResponseEntity<String> bookAppointmentFallBack() {
		return ResponseEntity.ok("Unable to bookAppointment right now...");
	}
}
