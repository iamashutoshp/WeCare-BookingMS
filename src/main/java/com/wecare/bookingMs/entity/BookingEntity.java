package com.wecare.bookingMs.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Booking")
public class BookingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookingId;

	private String userId;
	private String coachId;

	private LocalDate appointmentDate;
	private String slot;

	public BookingEntity() {
		super();
	}

	public BookingEntity(String userId, String coachId, LocalDate appointmentDate, String slot) {
		super();
		this.userId = userId;
		this.coachId = coachId;
		this.appointmentDate = appointmentDate;
		this.slot = slot;
	}

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
		return "BookingEntity [bookingId=" + bookingId + ", userId=" + userId + ", coachId=" + coachId
				+ ", appointmentDate=" + appointmentDate + ", slot=" + slot + "]";
	}

}
