package com.gogools.r3web.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class R3RestMsg {

	private String	result;
	private String	error;
}
