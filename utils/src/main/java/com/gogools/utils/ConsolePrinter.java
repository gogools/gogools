package com.gogools.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

@Deprecated
public class ConsolePrinter {

	public static String logHeader = "===== CONSOLE PRINTER : ";
	
	public static void prettyJson(Object object) {
		
		if(object == null) return;

		ObjectMapper mapper = new ObjectMapper();

		try {

			System.out.println(logHeader + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object));

		} catch (Exception e) {

			System.out.println(logHeader + "CONSOLE PRINTER - ERROR: " + e.getMessage());
		}
	}
	
	
	public static String getPrettyJson(Object object) {
		
		if(object == null) return null;

		ObjectMapper mapper = new ObjectMapper();

		try {

			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);

		} catch (Exception e) {

			return "CONSOLE PRINTER - ERROR";
		}
	}
	
	public static String obj2Console(Object object) {
		
		return object == null ? "NULL" : object.toString();
	}
}
