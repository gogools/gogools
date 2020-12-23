package com.gogools.chop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class GogoolsCtrl {

	@GetMapping("/template")
	public String getTemplate( Model model ) {
		
		return "template";
	}
	
	@GetMapping("/test")
	public String getTest( Model model ) {
		
		return "test";
	}
	
	@GetMapping("/fragments")
	public String getFragments( Model model ) {
		
		return "fragments/frags";
	}
}
