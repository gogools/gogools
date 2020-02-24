package com.gogools.enums;

import java.util.HashMap;
import java.util.Map;

public enum DaysOfWeekEnum {

	MONDAY		("Monday"),
	TUESDAY 	("Tuesday"),
	WEDNESDAY 	("Wednesday"),
	THURSDAY 	("Thursday"),
	FRIDAY 		("Friday"),
	SATURDAY 	("Saturday"),
	SUNDAY 		("Sunday");
	
	private final String option;
	
	private static class Holder {
		
        static Map<String, DaysOfWeekEnum> MAP = new HashMap<>();
    }

	
    private DaysOfWeekEnum(String option) {
    	
        Holder.MAP.put(option, this);
        this.option = option;
    }

    public String getValue() {
    	
    	return this.option;
    }
    
    @Override
    public String toString() {
    	
    	return this.option;
    }
    
    public static DaysOfWeekEnum findEnumByValue(String val) {
    	
    	DaysOfWeekEnum type = Holder.MAP.get(val.toLowerCase());
    		
        if(type == null) {
        	
        	return null;
        }
        
        return type;
    }
}