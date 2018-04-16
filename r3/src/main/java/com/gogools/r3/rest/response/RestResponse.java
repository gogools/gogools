package com.gogools.r3.rest.response;

import java.util.ArrayList;
import java.util.List;

import com.gogools.r3.domain.Operation;

import lombok.Data;

@Data
public class RestResponse {

	private List<Operation>	operations;
	private FrontMessage		msg;
	
	public RestResponse(List<Operation>	operations) {
		
		this.operations = operations;
	}
	
	public RestResponse(FrontMessage	msg) {
		
		operations = new ArrayList<Operation>();
		this.msg = msg;
	}
}
