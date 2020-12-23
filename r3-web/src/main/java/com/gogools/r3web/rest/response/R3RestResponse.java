package com.gogools.r3web.rest.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class R3RestResponse {

	private List<R3RestOperation>	operations;
	private R3RestMsg				msg;
}
