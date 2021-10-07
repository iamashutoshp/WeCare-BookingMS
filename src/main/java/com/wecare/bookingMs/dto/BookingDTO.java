package com.wecare.bookingMs.dto;

import java.time.LocalDate;

import com.wecare.bookingMs.entity.BookingEntity;

public class BookingDTO {

	private Integer bookingId;

	private String userId;
	private String coachId;

	private LocalDate appointmentDate;
	private String slot;

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCoachId() {
		return coachId;
	}

	public void setCoachId(String coachId) {
		this.coachId = coachId;
	}

	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getSlot() {
		return slot;
	}

	public void setSlot(String slot) {
		this.slot = slot;
	}

	@Override
	public String toString() {
		return "BookingDTO [bookingId=" + bookingId + ", userId=" + userId + ", coachId=" + coachId
				+ ", appointmentDate=" + appointmentDate + ", slot=" + slot + "]";
	}

	public static BookingEntity prepareBookingEntity(BookingDTO bookingDTO) {
		BookingEntity bookingEntity = new BookingEntity();

		bookingEntity.setAppointmentDate(bookingDTO.getAppointmentDate());
		bookingEntity.setCoachId(bookingDTO.getCoachId());
		bookingEntity.setSlot(bookingDTO.getSlot());
		bookingEntity.setUserId(bookingDTO.getUserId());

		return bookingEntity;
	}

	public static BookingDTO prepareBookingDTO(BookingEntity bookingEntity) {
		BookingDTO bookingDTO = new BookingDTO();

		bookingDTO.setAppointmentDate(bookingEntity.getAppointmentDate());
		bookingDTO.setCoachId(bookingEntity.getCoachId());
		bookingDTO.setSlot(bookingEntity.getSlot());
		bookingDTO.setUserId(bookingEntity.getUserId());
		bookingDTO.setBookingId(bookingEntity.getBookingId());

		return bookingDTO;
	}
}
