package com.gogools.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import com.gogools.porto.PortoAlertEnum;

import lombok.Data;

@Data
public class RestResponseDTO<T> {
	
	private Date						date;

	private String						msg;

	private T							data;

	private Map<String, List<String>>	errors;

	private HttpHeaders					headers;

	private HttpStatus					status;

	public RestResponseDTO() {
		
		this.date 	= new Date();
		this.msg 	= "";
		this.data 	= null;
		this.errors	= new HashMap<>();
	}
	
	public RestResponseDTO(String msg) {

		this.date 	= new Date();
		this.msg 	= msg;
		this.data 	= null;
		this.errors	= new HashMap<>();
	}
	
	public RestResponseDTO(String msg, T data) {

		this.date 	= new Date();
		this.msg 	= msg;
		this.data 	= data;
		this.errors	= new HashMap<>();
	}
	
	public RestResponseDTO(String msg, String error) {
		
		this.date 	= new Date();
		this.msg 	= msg;
		this.data 	= null;
		this.errors	= new HashMap<>();
		
		List<String> errorList = new ArrayList<>();
		errorList.add(error);
		
		this.errors.put("singleErrorMsg", errorList);
	}
	
	public RestResponseDTO(String msg, Map<String, List<String>> errors) {
		
		this.date 	= new Date();
		this.msg 	= msg;
		this.data 	= null;
		this.errors	= new HashMap<>();
	}

	public RestResponseDTO(String msg, T data, Map<String, List<String>> errors) {

		this.date 	= new Date();
		this.msg 	= msg;
		this.data 	= data;
		this.errors	= errors;
	}
	
	public RestResponseDTO(String msg, T data, Map<String, List<String>> errors, HttpHeaders headers, HttpStatus status) {

		this.date 		= new Date();
		this.msg 		= msg;
		this.data 		= data;
		this.errors		= errors;
		this.headers 	= headers;
		this.status 	= status;
	}
	
	public String getAlertClass() {
		
		if (status == null) return PortoAlertEnum.DARK.getHtmlClass();
		
		return status.equals(HttpStatus.OK) ? PortoAlertEnum.PRIMARY.getHtmlClass() : PortoAlertEnum.DANGER.getHtmlClass();
	}
	
}
