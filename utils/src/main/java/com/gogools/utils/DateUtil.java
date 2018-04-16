package com.gogools.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	public static enum DateFactor { 
		
		DAY(Calendar.DATE), MONTH(Calendar.MONTH), YEAR(Calendar.YEAR);
		
		int calendarValue;
		
		DateFactor(int calendarValue) {
			
			this.calendarValue = calendarValue;
		}
		
		public int getValue() {
			
			return this.calendarValue;
		}
	};

	public static final String DAY_MONTH_FORMAT 			= "M-dd";
	public static final String DEFAULT_FORMAT 			= "yyyy-M-dd";
	public static final String DEFAULT_DATE_TIME_FORMAT 	= "yyyy-M-dd HH:mm:ss";
	
	
	public static String getStringFromDate(Date date) {
		
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT);
		return sdf.format(date);
	}
	
	public static String getStringFromDate(Date date, String format) {
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}


	public static Date getDateFromString(String strDate) {

		return getDateFromString(strDate, DEFAULT_FORMAT);
	}
	
	public static Date getDateTimeFromString(String strDate) {

		return getDateFromString(strDate, DEFAULT_DATE_TIME_FORMAT);
	}

	
	public static Date getDateFromString(String strDate, String format) {
		
		if(strDate == null) return null;

		Date result = null;
		SimpleDateFormat formatter = new SimpleDateFormat(format);

		try {

			result = formatter.parse(strDate);

		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}
	
	
	public static Date addFactor(Date date, DateFactor factor, int qty) {
		
		Calendar c = Calendar.getInstance();
		
		c.setTime(date);
		c.add(factor.getValue(), qty);
		
		return c.getTime(); 
	}

	
	public static String getFirstDayOfTheWeekOnString() {
		
		LocalDateTime ldt = LocalDateTime.now();

		ZonedDateTime mexDateTime = ldt.atZone(ZoneId.of("-06:00")).with(DayOfWeek.MONDAY);
		
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(DEFAULT_FORMAT);
		
		return outputFormatter.format(mexDateTime);
	}
	
	
	public static String getLastDayOfTheWeekOnString() {
		
		LocalDateTime ldt = LocalDateTime.now();

		ZonedDateTime mexDateTime = ldt.atZone(ZoneId.of("-06:00")).with(DayOfWeek.MONDAY).plusWeeks(1);
		
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(DEFAULT_FORMAT);
		
		return outputFormatter.format(mexDateTime);
	}
	
	
	public static String getFirstDayOfTheMonthOnString() {
		
		LocalDateTime ldt = LocalDateTime.now();

		ZonedDateTime mexDateTime = ldt.atZone(ZoneId.of("-06:00")).withDayOfMonth(1);
		
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(DEFAULT_FORMAT);
		
		return outputFormatter.format(mexDateTime);
	}
	
	
	public static String getLastDayOfTheMonthOnString() {        
		
		LocalDate ldt = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
		
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(DEFAULT_FORMAT);
		
		return ldt.format(outputFormatter);
	}
	
	/**
	 * 
	 * @param dateToValidate, default format: dd-M-yyyy
	 * @return boolean
	 */
	public static boolean valiDate(String dateToValidate){
		
		boolean flag = true;

		if(dateToValidate == null){
			
			flag = false;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT);
		sdf.setLenient(false);

		try {

			sdf.parse(dateToValidate);

		} catch (ParseException e) {

			flag = false;
		}

		return flag;
	}
	
	
	public static String getCurrentMexDate() {
		
		LocalDateTime ldt = LocalDateTime.now();

		ZonedDateTime serverDateTime = ldt.atZone(ZoneId.systemDefault());
		ZonedDateTime mexDateTime 	= serverDateTime.withZoneSameInstant(ZoneOffset.of("-06:00"));
		
	    return DateTimeFormatter.ofPattern(DEFAULT_FORMAT).format(mexDateTime);
	}
	
	
	public static String getCurrentMexDateTime() {
		
		LocalDateTime ldt = LocalDateTime.now();

		ZonedDateTime serverDateTime = ldt.atZone(ZoneId.systemDefault());
		ZonedDateTime mexDateTime 	= serverDateTime.withZoneSameInstant(ZoneOffset.of("-06:00"));
		
	    return DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT).format(mexDateTime);
	}
	
	
	public static String getCurrentDateAtZone(ZoneId zone) {
		
		LocalDateTime ldt = LocalDateTime.now();

		ZonedDateTime zoneDateTime = ldt.atZone(zone);
		
		return DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT).format(zoneDateTime);
	}
	
	
	public static String getDayOfWeek(int value){

	    switch(value){
	    case 1:
	        return "Dom";
	    case 2:
	        return "Lun";
	    case 3:
	        return "Mar";
	    case 4:
	        return "Mie";
	    case 5:
	        return "Jue";
	    case 6:
	        return "Vie";
	    case 7:
	        return "Sab";
	    default:
	    		return "unk. day";
	    }
	}
}
