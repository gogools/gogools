package com.gogools.enums;

public enum Emj {

	OK("\uD83D\uDFE2"),
	INFO("\uD83D\uDD35"),
	WARN("\uD83D\uDFE0"),
	ERR("\uD83D\uDD34"),
	VIRUS("\uD83E\uDDA0");
	
	private Emj( String code ) {
		
		this.code = code;
	}
	
	private String code;
	
	public String toString() {
		
		return code;
	}
}
