package com.gogools.r3.constant;

import java.util.HashMap;
import java.util.Map;

public enum OperationType {
	
	OPERATION_START			("01", 8 ),
	CASH_CLOSING				("02", 11),
	TOKEN_ACCESS				("03", 7 ),
	USER_ACCESS				("04", 10),
	PHYSICAL_TOKEN_ACCESS	("05", 7 ),
	SENSOR_STATE				("06", 7 ),
	DEBUGEO					("08", 7 );
	
	private int	 		msgSize;
	private String	 	operation;
	
	
	private static class Holder {
		
        static Map<String, OperationType> MAP = new HashMap<>();
    }

	
    private OperationType(String op, int ms) {
    	
        Holder.MAP.put(op, this);
        this.msgSize = ms;
        this.operation = op;
    }
    
    
    public int getMessageSize() {
    	
    		return msgSize;
    }
    
    
    public String getOperation() {
    	
    		return operation;
    }

    
    public static OperationType findEnumByValue(String val) {
    	
    		OperationType type = Holder.MAP.get(val);
    		
        if(type == null) {
        	
        		throw new IllegalArgumentException("Operation unknown: [" + val + "]");
        }
        
        return type;
    }
}
