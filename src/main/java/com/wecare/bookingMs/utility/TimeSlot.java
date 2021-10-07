package com.wecare.bookingMs.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeSlot {

	private static Logger log=LoggerFactory.getLogger(TimeSlot.class);
	private Integer startTime;
	private Integer endTime;

	private String startMeridiem;
	private String endMeridiem;

	public static Boolean timeConflict(TimeSlot a, TimeSlot b) {
		boolean res = false;

		return res;
	}

	public static TimeSlot getSlot(String slot) {
		TimeSlot timeSlot=new TimeSlot();
		Matcher matcher=Pattern.compile("([\\d]+)\\s*([aApPmM])+\\s*").matcher(slot);
		
		if(matcher.find()) {
			timeSlot.startTime=Integer.parseInt(matcher.group(1));
			timeSlot.startMeridiem=matcher.group(2);
		}
		if(matcher.find()) {
			timeSlot.endTime=Integer.parseInt(matcher.group(1));
			timeSlot.endMeridiem=matcher.group(2);
		}
		
		return timeSlot;
	}
	
	public static Boolean isTimeSlotValid(String slot) {
		TimeSlot timeSlot=null;
		
		try {
			timeSlot=TimeSlot.getSlot(slot);
		} catch (Exception e) {
			log.error("Error in formatting Time Slot");
		}
		
		return timeSlot!=null;
	}

	public Integer getStartTime() {
		return startTime;
	}

	public void setStartTime(Integer startTime) {
		this.startTime = startTime;
	}

	public Integer getEndTime() {
		return endTime;
	}

	public void setEndTime(Integer endTime) {
		this.endTime = endTime;
	}

	public String getStartMeridiem() {
		return startMeridiem;
	}

	public void setStartMeridiem(String startMeridiem) {
		this.startMeridiem = startMeridiem;
	}

	public String getEndMeridiem() {
		return endMeridiem;
	}

	public void setEndMeridiem(String endMeridiem) {
		this.endMeridiem = endMeridiem;
	}

	@Override
	public String toString() {
		return "TimeSlot [startTime=" + startTime + ", endTime=" + endTime + ", startMeridiem=" + startMeridiem
				+ ", endMeridiem=" + endMeridiem + "]";
	}
	
}
