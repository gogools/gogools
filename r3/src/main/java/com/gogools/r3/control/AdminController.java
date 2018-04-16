package com.gogools.r3.control;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
    private ApplicationContext appContext;

	@RequestMapping(path="/show-loaded-beans", method = RequestMethod.GET)
	public @ResponseBody List<String> showLoadedBeans() {

		List<String> beans = Arrays.asList(appContext.getBeanDefinitionNames());
		
        return beans.stream()
        		 		.sorted()
        		 		.collect(Collectors.toList());
	}
}
