package com.gogools.r3web.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gogools.r3web.constant.Constants;
import com.gogools.r3web.rest.response.R3RestOperation;
import com.gogools.r3web.rest.response.R3RestResponse;
import com.gogools.utils.ConsolePrinter;

/*
 * > GET request to /operation-api/client/1 returns a list of operations with client ID 1
 * > GET request to /operation-api/client/1/date/today returns a list of operations with client ID 1 and sysDate TODAY
 * > GET request to /operation-api/client/1/date/01-01-2017 returns a list of operations with client ID 1 and sysDate 2017-01-01
 * > GET request to /operation-api/client/1/card/1/date/today returns a list of operations with client Id 1, card ID 1 and sysDate TODAY
 * > GET request to /operation-api/client/1/card/1/date/01-01-2017 returns a list of operations with client Id 1, card ID 1 and sysDate 01-01-2017
 * > GET request to /operation-api/client/1/card/1/op/01/date/today returns a list of operations with client ID 1 and card ID 1 and operation ID 01 and sysDate TODAY 	- ops:[01,02,03,04,05,06,08]
 * > GET request to /operation-api/client/1/card/1/op/01/date/week returns a list of operations with client ID 1 and card ID 1 and operation ID 01 and sysDate WEEK 		- ops:[01,02,03,04,05,06,08]
 * > GET request to /operation-api/client/1/card/1/op/01/date/month returns a list of operations with client ID 1 and card ID 1 and operation ID 01 and sysDate MONTH 	- ops:[01,02,03,04,05,06,08]
 * 
 * Ops
 * 01 - Start Operations
 * 02 - Cash Closing
 * 03 - Token Access
 * 04 - User Access
 * 05 - Physical Token Access
 * 06 - Sensor State
 * 08 - Debug
*/

@Service
public class R3RestApiService {
	
	protected final Log logger = LogFactory.getLog(getClass());

	private final RestTemplate restTemplate;
		
    private String username;

    private String password;
    
    @Value("${rest.auth.host}")
    private String host;

    @Value("${rest.auth.port}")
    private String port;
    
    private String serviceApi = "/operation-api";
	
	public R3RestApiService(RestTemplateBuilder restTemplateBuilder, @Value("${rest.auth.username}") String username, @Value("${rest.auth.password}") String password) {
		
		this.username = username;
		this.password = password;
		
        this.restTemplate = restTemplateBuilder.basicAuthorization(this.username, this.password).build();
    }
	
	
	public List<R3RestOperation> getTodaysCardData(String idClient, String idCard, String op) {
		
		return getCardData(idClient, idCard, op, Constants.REST_TODAY);
    }
	
	
	public List<R3RestOperation> getWeeklyCardData(String idClient, String idCard, String op) {
		
		return getCardData(idClient, idCard, op, Constants.REST_WEEK);
    }
	
	
	public List<R3RestOperation> getMonthlyCardData(String idClient, String idCard, String op) {
		
		return getCardData(idClient, idCard, op, Constants.REST_MONTH);
    }
	
	
	public List<R3RestOperation> getCardData(String idClient, String idCard, String op, String time) {
		
		logger.info("getCardData: idClient[" + idClient + "], idCard[" + idCard + "], op[" + op + "], time[" + time + "]");
		
		ResponseEntity<R3RestResponse> responseEntity =  this.restTemplate.getForEntity(host + ":" + port 
				+ serviceApi 
				+ "/client/" + idClient
				+ "/card/" + idCard 
				+ "/op/" + op 
				+ "/date/"+ time, R3RestResponse.class);
		
		R3RestResponse response = responseEntity.getBody();
		
		return response.getOperations();
	}

	
	public void testRestCall() {
		
		ResponseEntity<R3RestResponse> responseEntity =  this.restTemplate.getForEntity(host + ":" + port + "/operation-api/client/100"/*/card/100/date/today"*/, R3RestResponse.class);
		
		R3RestResponse objects = responseEntity.getBody();
		
		ConsolePrinter.prettyJson(objects.getClass().toString(), objects);
    }
}
