package com.gogools.wb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavController {
	
	@GetMapping("/")
	public String getHomePage() {

		return "dashboard";
	}

	@GetMapping("/coming-soon")
	public String getComingSoonPage() {

		return "coming-soon";
	}

	@GetMapping("/dashboard")
	public String getDashboardPage() {

		return "dashboard";
	}

	@GetMapping("/log-in")
	public String getLogInPage() {

		return "log-in";
	}

	@GetMapping("/profile")
	public String getProfilePage() {

		return "profile";
	}

	@GetMapping("/sign-up")
	public String getSignUpPage() {

		return "sign-up";
	}
}
