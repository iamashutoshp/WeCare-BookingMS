package com.wecare.bookingMs.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wecare.bookingMs.dto.BookingDTO;
import com.wecare.bookingMs.exceptions.BookingException;
import com.wecare.bookingMs.exceptions.CoachAvailabilityException;
import com.wecare.bookingMs.exceptions.InvalidTimeSlotException;
import com.wecare.bookingMs.responseModel.CustomResponse;
import com.wecare.bookingMs.service.BookingService;

@RestController
@RequestMapping("/booking")
@Validated
@CrossOrigin
public class BookingController {
	
	private Logger log=LoggerFactory.getLogger(BookingController.class);
	
	@Autowired
	private BookingService bookingService;

	@PutMapping(value="/{bookingId}",consumes = "application/json")
	public CustomResponse rescheduleAppointment(@PathVariable("bookingId") String bookingIdStr, @RequestBody BookingDTO bookingDTO) {
		log.info("rescheduleAppointment : rescheduling Appointment for bookingId : "+bookingIdStr);
		CustomResponse response=new CustomResponse();
		Boolean res=false;
		Integer bookingId;
		String slot=bookingDTO.getSlot();
		LocalDate dateOfAppointment=bookingDTO.getAppointmentDate();
		log.info("rescheduleAppointment : details for rescheduling -> new dateOfAppointment "+dateOfAppointment+" | new slot : "+slot);
		
		try {
			bookingId=Integer.parseInt(bookingIdStr);
			res=bookingService.rescheduleAppointment(bookingId,slot,dateOfAppointment);
		} catch (InvalidTimeSlotException | CoachAvailabilityException | NumberFormatException e) {
			response.setHttpStatus(HttpStatus.NOT_FOUND);
			response.setMessage(e.getMessage());
			response.setResult(false);
			e.printStackTrace();
		}
		
		if(res) {
			response.setHttpStatus(HttpStatus.OK);
			response.setMessage("Appointment rescheduled.");
			response.setResult(res);
		}
		
		log.info("rescheduleAppointment : returning response : "+response.toString());
		return response;
	}

	@DeleteMapping(value="/{bookingId}")
	public CustomResponse cancelAppointment(@PathVariable("bookingId") String bookingIdStr) {
		log.info("cancelAppointment : Inside cancelAppointment method for bookingId : "+bookingIdStr);
		Integer bookingId;
		CustomResponse response=new CustomResponse();
		response.setHttpStatus(HttpStatus.OK);
		response.setMessage("Appointment Deleted");
		try {
			bookingId=Integer.parseInt(bookingIdStr);
			bookingService.cancelAppointment(bookingId);
		} catch (NumberFormatException | BookingException e) {
			log.error("cancelAppointment : "+e.getMessage());
			response.setMessage("Invalid Booking number");
			log.error("cancelAppointment : could not delete appointment for bookingId : "+bookingIdStr);
		}
		
		log.info("cancelAppointment : exiting cancelAppointment");
		return response;
	}
	
	@GetMapping(value = "/coach_sched/{coachId}")
	public List<BookingDTO> getCoachSchedule(@PathVariable("coachId") String coachId){
		log.info("getCoachSchedule : inside getCoachSchedule Controller for coachId : "+coachId);
		List<BookingDTO> bookingDTOs=new ArrayList<>();
		
		try {
			bookingDTOs=bookingService.getCoachSchedule(coachId);
		} catch (BookingException e) {
			log.error("getCoachSchedule : "+e.getMessage());
			e.printStackTrace();
		}
		
		log.info("getCoachSchedule : returning bookingDTOs : "+bookingDTOs + " for coachId : "+coachId);
		return bookingDTOs;
	}
}
