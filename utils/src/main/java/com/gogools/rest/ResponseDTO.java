package com.gogools.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import com.gogools.porto.PortoAlertEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO<T> {

	private String						msg;

	private T							data;

	private Map<String, List<String>>	errors;

	private HttpHeaders					headers;

	private HttpStatus					status;
	
	public String getAlertClass() {
		
		return status.equals(HttpStatus.OK) ? PortoAlertEnum.PRIMARY.getHtmlClass() : PortoAlertEnum.DANGER.getHtmlClass();
	}
}
