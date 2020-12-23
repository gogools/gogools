package com.gogools.r3web.control;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gogools.r3web.service.TodayFrontService;
import com.gogools.r3web.service.SessionDataService;

@Controller
public class WebController {
	
	@Autowired
	private SessionDataService 	sessionService;
	
	@Autowired
	private TodayFrontService 	cardInfoService;

	@RequestMapping("/init")
	private String init(Map<String, Object> model) {
		
		String userName 				= ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		List<String> authorities 	= ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAuthorities().stream()
																																.map(a -> a.getAuthority())
																																.collect(Collectors.toList());

		sessionService.setSessionData(userName, authorities);		
		
		if( !sessionService.getUserIsActive() ) {
			
			return "redirect:/userblocked";
			
		} else {
			
			return "redirect:/home";
		}
	}
	
	
	@RequestMapping("/")
	public String root(Map<String, Object> model) {
		
		return "redirect:/home";
	}
	
	
	@RequestMapping("/home")
	public String home(Map<String, Object> model) {
		
		model.put("userName", sessionService.getUserName());
		model.put("userEmail", sessionService.getEmail());
		model.put("todaysCardData", cardInfoService.getViewData());
		model.put("test", "1,000,000");
		
		return "today";
	}
	
	
	@RequestMapping("/userblocked")
	public String userBlocked(Map<String, Object> model) {
		
		return "userblocked";
	}

	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {

		if (error != null) {

			model.addAttribute("error", "Your username or password is invalid.");
		}

		if (logout != null) {

			model.addAttribute("message", "You have been logged out successfully.");
		}

		return "login";
	}
}
