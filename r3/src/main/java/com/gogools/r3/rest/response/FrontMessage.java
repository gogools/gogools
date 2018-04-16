package com.gogools.r3.rest.response;

import lombok.Data;

@Data
public class FrontMessage {

	private String result;
	private String error;
	
	public static FrontMessage prepareResult(String result) {
		
		FrontMessage fm = new FrontMessage();
		fm.setResult(result);
		
		return fm;
	}
	
	public static FrontMessage prepareErrorMsg(String error) {
		
		FrontMessage fm = new FrontMessage();
		fm.setError(error);
		
		return fm;
	}
}
