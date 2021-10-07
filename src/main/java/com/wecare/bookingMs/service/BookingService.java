package com.wecare.bookingMs.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wecare.bookingMs.entity.BookingEntity;
import com.wecare.bookingMs.exceptions.CoachAvailabilityException;
import com.wecare.bookingMs.exceptions.InvalidTimeSlotException;
import com.wecare.bookingMs.repo.BookingRepo;
import com.wecare.bookingMs.utility.TimeSlot;

@Service
public class BookingService {

	private Logger log = LoggerFactory.getLogger(BookingService.class);

	@Autowired
	private BookingRepo bookingRepo;

	@Transactional
	public Boolean bookAppointment(String userId, String coachId, String slot, LocalDate dateOfAppointment)
			throws CoachAvailabilityException, InvalidTimeSlotException {
		log.info("inside bookAppointment service for userId : " + userId + " with coachId : " + coachId + " on date : "
				+ dateOfAppointment + " slot : " + slot);
		Boolean res = false;
		List<BookingEntity> bookings = null;
		BookingEntity bookingEntity = null;
		try {
			bookings = bookingRepo.findByCoachIdAndAppointmentDateAndSlot(coachId, dateOfAppointment, slot);
		} catch (Exception e) {
			log.error("error in Booking an Appointment...");
			e.printStackTrace();
		}

		if (bookings != null && bookings.size() > 0)
			throw new CoachAvailabilityException(
					"Booking Failed: Coach is not avialable for the given Date or Time slot");

		if (!TimeSlot.isTimeSlotValid(slot))
			throw new InvalidTimeSlotException("Time Slot Format is Invalid...");

		bookingEntity = new BookingEntity(userId, coachId, dateOfAppointment, slot);
		bookingRepo.saveAndFlush(bookingEntity);

		log.info("Appointment Booked -> Booking detail : " + bookingEntity);
		res = true;
		return res;
	}

	@Transactional
	public Boolean rescheduleAppointment(Integer bookingId, String slot, LocalDate dateOfAppointment)
			throws InvalidTimeSlotException, CoachAvailabilityException {
		log.info("inside rescheduleAppointment for bookingId : " + bookingId);
		Boolean res = true;
		Optional<BookingEntity> optionalBookingEntity = null;
		BookingEntity bookingEntity = null;
		List<BookingEntity> bookings = null;

		if (!TimeSlot.isTimeSlotValid(slot))
			throw new InvalidTimeSlotException("Time Slot Format is Invalid...");

		try {
			optionalBookingEntity = bookingRepo.findById(bookingId);
			if (optionalBookingEntity.isPresent())
				bookingEntity = optionalBookingEntity.get();
			log.info("rescheduleAppointment : booking to be rescheduled : " + bookingEntity);
			
			if (bookingEntity != null)
				bookings = bookingRepo.findByCoachIdAndAppointmentDateAndSlot(bookingEntity.getCoachId(),
						dateOfAppointment, slot);
			log.info("rescheduleAppointment : coach appointments if already present for requested Time & slots : "
					+ bookings);
			
		} catch (Exception e) {
			log.error("RescheduleAppointment : error in fetching data for bookingId : " + bookingId);
		}
		if (bookings != null && bookings.size() > 0)
			throw new CoachAvailabilityException(
					"Appointment Rescheduling Failed: Coach is not avialable for the given Date or Time slot");

		bookingRepo.updateSlotAndAppointmentDate(slot, dateOfAppointment, bookingId);
		log.info("RescheduleAppointment done for bookingId : " + bookingId);
		return res;
	}

}
