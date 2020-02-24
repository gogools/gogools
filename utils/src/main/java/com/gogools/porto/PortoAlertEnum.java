package com.gogools.porto;

public enum PortoAlertEnum {

	PRIMARY ("alert alert-primary"),  
	SUCCESS ("alert alert-success"), 
	INFO	("alert alert-info"), 
	WARNING	("alert alert-warning"), 
	DANGER	("alert alert-danger"), 
	DARK	("alert alert-dark");
	
	private String htmlClass;
	
	private PortoAlertEnum(String htmlClass) {
		
		this.htmlClass 	= htmlClass;
	}

	public String getHtmlClass() {
		
		return htmlClass;
	}
}
