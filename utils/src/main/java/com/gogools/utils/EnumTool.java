package com.gogools.utils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class EnumTool {
	
	public static interface EnumToolInterface {
		
		public String getValue();
		public Integer getOrdinal();
	}
	
	
	private static <T extends EnumToolInterface> Stream<T> collectionAsStream( T[] array ) {
	    return array == null
	      ? Stream.empty() 
	      : Stream.of(array);
	}
	
	
	public static <T extends EnumToolInterface> T getEnumFromValue( Class<T> c , String value ) {
		
		return c.isEnum() ? collectionAsStream( c.getEnumConstants() )
								.filter( x -> x.getValue().equals(value) )
								.findFirst()
								.orElse( null )
						  :
							null;
	}
	
	
	public static <T extends EnumToolInterface> List<String> getListEnumValues( Class<T> c ) {
		
		return c.isEnum() ? collectionAsStream( c.getEnumConstants() )
								.map(x -> x.getValue() )
								.collect( Collectors.toList() )
						  :
							Collections.<String> emptyList();
	}
	
	
	public static <T extends EnumToolInterface> Map<Integer, String> getMapEnumValues( Class<T> c ) {
		
		return c.isEnum() ? collectionAsStream( c.getEnumConstants() )
								.collect( Collectors.toMap(EnumToolInterface::getOrdinal, EnumToolInterface::getValue) )
						  :
							Collections.<Integer, String>emptyMap();
	}
}
