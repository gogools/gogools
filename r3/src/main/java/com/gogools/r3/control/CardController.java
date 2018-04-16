package com.gogools.r3.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gogools.r3.service.OperationService;

@Controller
@RequestMapping("/feedMe")
public class CardController {
	
	@Autowired
	private OperationService opService;


	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody FeederResponse cardFeederGet(@RequestParam(value = "data", required = false, defaultValue = "") String data) {

		opService.saveOperation(data);
		
		return FeederResponse.createSucessResponse(null);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody FeederResponse cardFeederPost(@RequestParam(value = "data", required = false, defaultValue = "") String data) {

		opService.saveOperation(data);
		
		return FeederResponse.createSucessResponse(null);
	}
}
