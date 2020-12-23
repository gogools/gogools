package com.app.enums;

import java.util.HashMap;
import java.util.Map;

public enum UserTypeEnum {

	ALUMNO		("Alumno"),
	ADMIN 		("Administrador"),
	COORDINADOR ("Coordinador"),
	MAESTRO		("Maestro"),
	SUPER		("SuperUser");
	
	private final String option;
	
	private static class Holder {
		
        static Map<String, UserTypeEnum> MAP = new HashMap<>();
    }

	
    private UserTypeEnum(String option) {
    	
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
    
    public static UserTypeEnum findEnumByValue(String val) {
    	
    	UserTypeEnum type = Holder.MAP.get(val.toLowerCase());
    		
        if(type == null) {
        	
        	return null;
        }
        
        return type;
    }
}
