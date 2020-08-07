package com.gogools.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * The Gogools Date Util Class
 * @author dpulido, 2018
 *
 */
public class GDT {
	
	public static enum DateFactor { 
		
		HOUR(	Calendar.HOUR	), 
		MIN(	Calendar.MINUTE	), 
		SEC(	Calendar.SECOND	), 
		DAY(	Calendar.DATE	), 
		MONTH(	Calendar.MONTH	), 
		YEAR(	Calendar.YEAR	);
		
		int calendarValue;
		
		DateFactor(int calendarValue) {
			
			this.calendarValue = calendarValue;
		}
		
		public int getValue() {
			
			return this.calendarValue;
		}
	};
	
	public static enum DayOfWeek {
		
		MONDAY(		1 ),
		TUESDAY(	2 ),
		WEDNESDAY(	3 ),
		THURSDAY(	4 ),
		FRIDAY(		5 ),
		SATURDAY( 	6 ),
		SUNDAY(		0 );
		
		int day;
		
		DayOfWeek(int day) {
			
			this.day = day;
		}
		
		public int getValue() {
			
			return this.day;
		}
	};


	public static final String DAY_MONTH_FORMAT 	= "dd-MM";
	public static final String DEFAULT_FORMAT 		= "yyyy-MM-dd";
	public static final String DATE_TIME_FORMAT 	= "yyyy-MM-dd HH:mm:ss";
	
	
	public static String date2String(Date date) {
		
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT);
		return sdf.format(date);
	}
	
	public static String date2String(Date date, String format) {
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}


	public static Date string2Date(String strDate) {

		return string2Date(strDate, DEFAULT_FORMAT);
	}
	
	public static Date string2Datetime(String strDate) {

		return string2Date(strDate, DATE_TIME_FORMAT);
	}

	
	public static Date string2Date(String strDate, String format) {
		
		if(strDate == null || format == null) return null;

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
	
	
	private static Calendar getWeekBaseDay() {
		
		Calendar baseDay =  Calendar.getInstance();
		baseDay.set(Calendar.DAY_OF_WEEK, baseDay.getFirstDayOfWeek());
		baseDay.set(Calendar.HOUR_OF_DAY, 0);
		baseDay.set(Calendar.MINUTE, 0);
		baseDay.set(Calendar.SECOND, 0);
		baseDay.set(Calendar.MILLISECOND, 0);
		
		return baseDay;
	}
	
	
	private static Calendar getWeekBaseDay( Date date ) {
		
		Calendar baseDay =  Calendar.getInstance();
		baseDay.setTime(date);
		
		baseDay.set(Calendar.DAY_OF_WEEK, baseDay.getFirstDayOfWeek());
		baseDay.set(Calendar.HOUR_OF_DAY, 0);
		baseDay.set(Calendar.MINUTE, 0);
		baseDay.set(Calendar.SECOND, 0);
		baseDay.set(Calendar.MILLISECOND, 0);
		
		return baseDay;
	}
	
	
	private static Calendar getMonthBaseDay() {
		
		Calendar baseDay =  Calendar.getInstance();
		baseDay.set(Calendar.DAY_OF_MONTH, 1);
		baseDay.set(Calendar.HOUR_OF_DAY, 0);
		baseDay.set(Calendar.MINUTE, 0);
		baseDay.set(Calendar.SECOND, 0);
		baseDay.set(Calendar.MILLISECOND, 0);
		
		return baseDay;
	}
	
	
	private static Calendar getMonthBaseDay( Integer monthsWindow ) {
		
		Calendar baseDay =  Calendar.getInstance();
		baseDay.set(Calendar.DAY_OF_MONTH, 1);
		baseDay.set(Calendar.HOUR_OF_DAY, 0);
		baseDay.set(Calendar.MINUTE, 0);
		baseDay.set(Calendar.SECOND, 0);
		baseDay.set(Calendar.MILLISECOND, 0);
		
		baseDay.add(Calendar.MONTH, monthsWindow);
		
		return baseDay;
	}
	
	
	private static Calendar getYearBaseDay() {
		
		Calendar baseDay =  Calendar.getInstance();
		baseDay.set(Calendar.DAY_OF_YEAR, 1);
		baseDay.set(Calendar.HOUR_OF_DAY, 0);
		baseDay.set(Calendar.MINUTE, 0);
		baseDay.set(Calendar.SECOND, 0);
		baseDay.set(Calendar.MILLISECOND, 0);
		
		return baseDay;
	}

	
	public static Date dayOfThisWeek( DayOfWeek d ) {
		
		Calendar cal = getWeekBaseDay();
		cal.add(Calendar.DATE, d.getValue());
		
		return cal.getTime();
	}
	
	
	public static Date dayOfThisWeek( DayOfWeek d, Integer hour, Integer min, Integer sec ) {
		
		Calendar cal = getWeekBaseDay();
		cal.add(Calendar.DATE, d.getValue());
		
		return cal.getTime();
	}

	
	public static Date firstDayOfThisWeek() {
		
		Calendar cal = getWeekBaseDay();
		
		return cal.getTime();
	}
	
	
	public static Date firstDayOfThisWeek( Date date ) {
		
		Calendar cal = getWeekBaseDay( date );
		
		return cal.getTime();
	}
	
	
	public static Date firstWeekDayOfThisWeek() {
		
		Calendar cal = getWeekBaseDay();
		cal.add(Calendar.DATE, 1);
		
		return cal.getTime();
	}
	
	
	public static Date lastWeekDayOfThisWeek() {
		
		Calendar cal = getWeekBaseDay();
		cal.add(Calendar.DATE, 5);
		
		return cal.getTime();
	}
	
	
	public static Date lastDayOfThisWeek() {
		
		Calendar cal = getWeekBaseDay();
		cal.add(Calendar.DATE, GDT.DayOfWeek.SATURDAY.getValue());
		
		return cal.getTime();
	}
	
	
	public static Date firstDayOfThisMonth() {
		
		Calendar cal = getMonthBaseDay();
		
		return cal.getTime();
	}
	
	
	public static Date firstDayOfThisMonth( Integer monthsWindow ) {
		
		Calendar cal = getMonthBaseDay( monthsWindow );
		
		return cal.getTime();
	}
	
	
	public static Date firstDayOfThisYear() {
		
		Calendar cal = getYearBaseDay();
		
		return cal.getTime();
	}

	
	public static Date lastDayOfThisMonth() {        
		
		Calendar cal = getMonthBaseDay();
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DATE, -1);
		
		return cal.getTime();
	}
	
	/**
	 * 
	 * @param dateToValidate, default format: yyyy-MM-dd
	 * @return boolean, true is it does or false if doesn't
	 */
	public static boolean valiDate(String theDate){
		
		boolean flag = true;

		if(theDate == null){
			
			flag = false;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT);
		sdf.setLenient(false);

		try {

			sdf.parse(theDate);

		} catch (ParseException e) {

			flag = false;
		}

		return flag;
	}
	
	
	public static String datetimeAtZone(ZoneId zone) {
		
		LocalDateTime ldt = LocalDateTime.now();

		ZonedDateTime zoneDateTime = ldt.atZone(zone);
		
		return DateTimeFormatter.ofPattern(DATE_TIME_FORMAT).format(zoneDateTime);
	}
}
