package com.wecare.bookingMs.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wecare.bookingMs.entity.BookingEntity;

@Repository
public interface BookingRepo extends JpaRepository<BookingEntity, Integer>{
	
	List<BookingEntity> findByCoachIdAndAppointmentDateAndSlot(String coachId,LocalDate appointmentDate,String slot);
}

