package com.gogools;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataPrinter {
	
	
	private static String format( String str ) {
		
		return MessageFormat.format(Constants.DATA_2_LOG, str);
	}

	
	public static String consoleIt(String name, List<Object> info) {
		
		return format( name.toUpperCase() + " : [" + info.stream().map( Object::toString ).collect( Collectors.joining(", ")) + "]" );
	}
	
	
	public static String consoleIt(String name, Object info) {
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			
			return format( name.toUpperCase() + " : " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(info));
			
		} catch (JsonProcessingException e) {
			
			return e.getMessage();
		}
	}
	
	
	public static String consoleIt(String... data) {
		
		return format( Arrays.stream(data).map( Object::toString ).collect( Collectors.joining(", ")) );
	}
}
