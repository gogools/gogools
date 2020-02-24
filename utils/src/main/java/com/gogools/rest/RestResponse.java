package com.gogools.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class RestResponse<T> {

	private String	msg;

	private T		data;
	
	private Map<String, List<String>> errors;
	
	public RestResponse() {
		
		this.msg 	= "";
		this.data 	= null;
		this.errors	= new HashMap<>();
	}
	
	public RestResponse(String msg) {

		this.msg 	= msg;
		this.data 	= null;
		this.errors	= new HashMap<>();
	}
	
	public RestResponse(String msg, T data) {

		this.msg 	= msg;
		this.data 	= data;
		this.errors	= new HashMap<>();
	}
	
	public RestResponse(String msg, String error) {
		
		this.msg 	= msg;
		this.data 	= null;
		this.errors	= new HashMap<>();
		
		List<String> errorList = new ArrayList<>();
		errorList.add(error);
		
		this.errors.put("singleErrorMsg", errorList);
	}
	
	public RestResponse(String msg, Map<String, List<String>> errors) {
		
		this.msg 	= msg;
		this.data 	= null;
		this.errors	= new HashMap<>();
	}

	public RestResponse(String msg, T data, Map<String, List<String>> errors) {

		this.msg 	= msg;
		this.data 	= data;
		this.errors	= errors;
	}
}
