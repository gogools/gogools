package com.gogools.r3.control;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gogools.r3.domain.Operation;
import com.gogools.r3.rest.response.FrontMessage;
import com.gogools.r3.rest.response.RestResponse;
import com.gogools.r3.service.OperationService;
import com.gogools.utils.DateUtil;

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

@Controller
@RequestMapping("/operation-api")
public class OperationController {

	public static final Logger logger = LoggerFactory.getLogger(OperationController.class);
	
	@Autowired
	private OperationService opService;
	
	//-- Cliente req
	@RequestMapping(value = "/client/{idClient}", method = RequestMethod.GET)
    public ResponseEntity<?> getOperationsByClient(@PathVariable("idClient") String idClient) {
		
		ResponseEntity<?> validationResult = null;
        logger.info("Fetching operations with client id {}", idClient);
        
        // validations
        if((validationResult = validateIdClient(idClient)) != null) return validationResult;
        
        Stream<Operation> ops = opService.getOperationsByClient(idClient);
        
        // do something to the data
        List<Operation> list = ops.collect(Collectors.toList());
        
        if (list.isEmpty()) {
        	
            logger.info("Operations with idClient {} not found.", idClient);
            return new ResponseEntity<RestResponse>(new RestResponse(FrontMessage.prepareResult("Operations with IDCLIENT " + idClient + " not found")), HttpStatus.OK);
        }
        
        return new ResponseEntity<RestResponse>(new RestResponse(list), HttpStatus.OK);
    }
	
	//-- Client by Date req
	@RequestMapping(value = "/client/{idClient}/date/today", method = RequestMethod.GET)
    public ResponseEntity<?> getOperationsByClientToday(@PathVariable("idClient") String idClient) {
        
        return opsWithClientsAndDates(idClient, DateUtil.getCurrentMexDate(), null);
    }
	
	
	@RequestMapping(value = "/client/{idClient}/date/{sysDate}", method = RequestMethod.GET)
    public ResponseEntity<?> getOperationsByClientAndSysDate(@PathVariable String idClient, @PathVariable String sysDate) {		        
        
        return opsWithClientsAndDates(idClient, sysDate, null);
    }
	
	//-- Client and Card by Date req
	@RequestMapping(value = "/client/{idClient}/card/{idCard}/date/today", method = RequestMethod.GET)
    public ResponseEntity<?> getOperationsByClientAndCardToday(@PathVariable String idClient, @PathVariable String idCard) {
		
        return opsWithClientsCardAndDates(idClient, idCard, DateUtil.getCurrentMexDate(), null);
    }
	
	
	@RequestMapping(value = "/client/{idClient}/card/{idCard}/date/{sysDate}", method = RequestMethod.GET)
    public ResponseEntity<?> getOperationsByClientAndCardDate(@PathVariable String idClient, @PathVariable String idCard, @PathVariable String sysDate) {
		
        return opsWithClientsCardAndDates(idClient, idCard, sysDate, null);
    }
	
	//-- Client and Card and Operation by Date req
	@RequestMapping(value = "/client/{idClient}/card/{idCard}/op/{op}/date/today", method = RequestMethod.GET)
    public ResponseEntity<?> getOperationsByClientAndCardAndOpToday(@PathVariable String idClient, @PathVariable String idCard, @PathVariable String op) {
		
        return opsWithClientsCardOpsAndDates(idClient, idCard, op, DateUtil.getCurrentMexDate(), null);
    }
	
	
	@RequestMapping(value = "/client/{idClient}/card/{idCard}/op/{op}/date/{sysDate}", method = RequestMethod.GET)
    public ResponseEntity<?> getOperationsByClientAndCardAndOpByDate(@PathVariable String idClient, @PathVariable String idCard, @PathVariable String op, @PathVariable String sysDate) {
		
        return opsWithClientsCardOpsAndDates(idClient, idCard, op, sysDate, null);
    }
	
	
	@RequestMapping(value = "/client/{idClient}/card/{idCard}/op/{op}/date/week", method = RequestMethod.GET)
    public ResponseEntity<?> getOperationsByClientAndCardAndOpByWeek(@PathVariable String idClient, @PathVariable String idCard, @PathVariable String op) {
		
		return opsWithClientsCardOpsAndDates(idClient, idCard, op, DateUtil.getFirstDayOfTheWeekOnString(), DateUtil.getLastDayOfTheWeekOnString());
    }
	
	
	@RequestMapping(value = "/client/{idClient}/card/{idCard}/op/{op}/date/month", method = RequestMethod.GET)
    public ResponseEntity<?> getOperationsByClientAndCardAndOpByMonth(@PathVariable String idClient, @PathVariable String idCard, @PathVariable String op) {
		
		return opsWithClientsCardOpsAndDates(idClient, idCard, op, DateUtil.getFirstDayOfTheMonthOnString(), DateUtil.getLastDayOfTheMonthOnString());
    }
	
	
	private ResponseEntity<?> opsWithClientsAndDates(String idClient, String sysDate, String windowSysDate) {
		
		ResponseEntity<?> validationResult = null;
		logger.info("Fetching operations with client id {} and dates {} - {}", idClient, sysDate, windowSysDate);        

		// validations
		if((validationResult = validateIdClient(idClient)) 	!= null) return validationResult;
		if((validationResult = validateDate(sysDate)) 		!= null) return validationResult;
        
        Stream<Operation> ops = opService.getOperationsByClientAndSysDate(idClient, 
        																	DateUtil.getDateFromString(sysDate), 
        																	windowSysDate != null ? DateUtil.getDateFromString(windowSysDate) : null);
        
        // do something to the data
        List<Operation> list = ops.collect(Collectors.toList());
        
        if (list.isEmpty()) {
        	
            logger.info("Operations with idClient {} and dates {} - {} not found.", idClient, sysDate, windowSysDate);
            return new ResponseEntity<RestResponse>(new RestResponse(FrontMessage.prepareResult("Operations with idClient " + idClient + " and dates " + sysDate + " - " + windowSysDate + " not found")), HttpStatus.OK);
        }
        
        return new ResponseEntity<RestResponse>(new RestResponse(list), HttpStatus.OK);
	}
	
	
	private ResponseEntity<?> opsWithClientsCardAndDates(String idClient, String idCard, String sysDate, String windowSysDate) {
		
		ResponseEntity<?> validationResult = null;
		logger.info("Fetching today operations with client id {} and card {} and dates {} - {}", idClient, idCard, sysDate, windowSysDate);
		
		// validations
		if((validationResult = validateIdClient(idClient)) 	!= null) return validationResult;
		if((validationResult = validateIdCard(idCard)) 		!= null) return validationResult;
		if((validationResult = validateDate(sysDate)) 		!= null) return validationResult;
        
        Stream<Operation> ops = opService.getOperationsByClientCardAndSysDate(idClient, 
        																		idCard, 
        																		DateUtil.getDateFromString(sysDate), 
        																		DateUtil.getDateFromString(windowSysDate));
        
        // do something to the data
        List<Operation> list = ops.collect(Collectors.toList());
        
        if (list.isEmpty()) {
        	
        		logger.info("Operations with idClient {} and idCard {} and dates {} - {} not found.", idClient, idCard, sysDate, windowSysDate);
        		return new ResponseEntity<RestResponse>(new RestResponse(FrontMessage.prepareResult("Operations with idClient " + idClient + " and idCard " + idCard + " and dates " + sysDate + " - " + windowSysDate + " not found")), HttpStatus.OK);
        }
        
        return new ResponseEntity<RestResponse>(new RestResponse(list), HttpStatus.OK);
	}
	
	
	private ResponseEntity<?> opsWithClientsCardOpsAndDates(String idClient, String idCard, String op, String sysDate, String windowSysDate) {
		
		ResponseEntity<?> validationResult = null;
		logger.info("Fetching today operations with client id {} and card {} and operation {} and dates {} - {}", idClient, idCard, op, sysDate, windowSysDate);
		
		if((validationResult = validateIdClient(idClient)) 	!= null) return validationResult;
		if((validationResult = validateIdCard(idCard)) 		!= null) return validationResult;
		if((validationResult = validateDate(sysDate)) 		!= null) return validationResult;
        
        Stream<Operation> ops = opService.getOperationsByClientCardOpAndSysDate(idClient, 
        																		idCard, 
        																		op,
        																		DateUtil.getDateFromString(sysDate), 
        																		DateUtil.getDateFromString(windowSysDate));
        
        // do something to the data
        List<Operation> list = ops.collect(Collectors.toList());
        
        if (list.isEmpty()) {
        	
        		logger.info("Operations with idClient {} and idCard {} and operation {} and dates {} - {} not found.", idClient, idCard, op, sysDate, windowSysDate != null ? windowSysDate : "today");
        		return new ResponseEntity<RestResponse>(new RestResponse(FrontMessage.prepareResult("Operations with idClient " + idClient + " and idCard " + idCard + " and operation " + op +" and dates " + sysDate + " - " + windowSysDate + " not found")), HttpStatus.OK);
        }
        
        return new ResponseEntity<RestResponse>(new RestResponse(list), HttpStatus.OK);
	}
	
	
	//-- INPUT VALIDATIONS
	private ResponseEntity<?> validateIdClient(String idClient) {
		
		if( idClient == null ) {
        	
        	 	logger.error("IdClient missing {}", idClient);
            return new ResponseEntity<RestResponse>(new RestResponse(FrontMessage.prepareErrorMsg("Error on IdClient [" + idClient + "]")), HttpStatus.BAD_REQUEST);
        }
		
		return null;
	}
	
	
	private ResponseEntity<?> validateIdCard(String idCard) {
		
		if( idCard == null ) {
			
			logger.error("IdClient missing {}", idCard);
			return new ResponseEntity<RestResponse>(new RestResponse(FrontMessage.prepareErrorMsg("Error on idCard [" + idCard + "]")), HttpStatus.BAD_REQUEST);
		}
		
		return null;
	}
	
	
	private ResponseEntity<?> validateDate(String date) {
		
		if( date != null && !DateUtil.valiDate(date) ) {
        	
        	 	logger.error("Date {} bad formatted.", date);
            return new ResponseEntity<RestResponse>(new RestResponse(FrontMessage.prepareErrorMsg("Start date [" + date + "] bad formatted")), HttpStatus.BAD_REQUEST);
        }
		
		return null;
	}
	
	
}
