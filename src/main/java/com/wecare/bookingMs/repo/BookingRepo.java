package com.wecare.bookingMs.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wecare.bookingMs.entity.BookingEntity;

@Repository
public interface BookingRepo extends JpaRepository<BookingEntity, Integer>{
	
	List<BookingEntity> findByCoachIdAndAppointmentDateAndSlot(String coachId,LocalDate appointmentDate,String slot);
	
	@Modifying
    @Query("Update BookingEntity b SET b.slot=:slot, b.appointmentDate=:appointmentDate WHERE b.bookingId=:bookingId")
    public void updateSlotAndAppointmentDate(String slot,LocalDate appointmentDate,Integer bookingId);
}

