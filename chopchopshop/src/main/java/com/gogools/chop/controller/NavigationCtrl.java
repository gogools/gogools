package com.gogools.chop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gogools.chop.dto.EventRegistryDTO;
import com.gogools.chop.dto.ProductDTO;
import com.gogools.chop.form.EventForm;
import com.gogools.chop.service.ChopBkendClientSrvc;
import com.gogools.enums.Emj;
import com.gogools.rest.RestResponseDTO;
import com.gogools.sb.service.mail.MailService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class NavigationCtrl {
	
	@Autowired
	private ChopBkendClientSrvc service;
	
	@Autowired
	private MailService emailService;
	
	@Value("#{'${chopshop.emails}'.split(',')}") 
	private List<String> chopshopEmails;

	@GetMapping("/")
	public String getHome() {

		return "index";
	}

	@GetMapping("/about")
	public String getAbout() { 

		return "about";
	}

	@GetMapping("/contact")
	public String getContact() {

		return "contact";
	}

	@GetMapping("/faq")
	public String getFaq() {

		return "faq";
	}

	@GetMapping("/products")
	public String getProducts(Model model) {

		RestResponseDTO<List<ProductDTO>> dto = service.getAllProducts();
		
		if( dto.getStatus().equals(HttpStatus.OK) ) {
			
			log.info(Emj.ERR + " Fetched products[{}]: {} - {}", dto.getData().size(), dto.getStatus(), dto.getMsg());
			
			model.addAttribute("products", dto);
		}
		else {
			
			model.addAttribute("products", new ArrayList<>());
		}
		
		return "products";
	}

	@GetMapping("/bushiroad")
	public String getEvent() {

		return "index";
	}
	
	@GetMapping("/shop")
	public String getShop( Model model ) {
		
		return "shop";
	}
	
	@GetMapping("/event")
	public String getEvent(@RequestParam(required = false) String id, Model model ) {
		
		return "event";
	}
	
	@GetMapping("/maintenance")
	public String getMaintenance( Model model ) {
		
		return "maintenance";
	}

	@PostMapping("/event")
	public String register( @Valid EventForm ef, BindingResult bindingResult, Model model ) {
	
		log.info(Emj.INFO + " EventForm: {}", ef);
		
		if (bindingResult.hasErrors()) {
			
			return "fragments/frags :: errors";
        }
		
		EventRegistryDTO er = new ModelMapper().map(ef, EventRegistryDTO.class);
		
		RestResponseDTO<EventRegistryDTO> dto = service.createEventRegistry(er);
		
		if( !dto.getStatus().equals(HttpStatus.CREATED) ) {
			
			log.info(Emj.ERR + " Create event registry error: {}", dto.getMsg());
			return "fragments/frags :: errors";
		}
		
		EventRegistryDTO savedEr = dto.getData();
		
		List<String> to = new ArrayList<>();
		to.add(savedEr.getEmail());
		
		List<String> bcc = new ArrayList<>();
		
		for(String email : chopshopEmails) {
			bcc.add(email);
		}
		
		List<String> clientParams = new ArrayList<>();
		clientParams.add(savedEr.getEventCode());
		clientParams.add(savedEr.getEventDesc());
		
		Boolean clientMail = emailService.sendMail(to, 
								bcc, 
								"Torneo del Circuito Bushiroad México, registro #CBM-2020-" + savedEr.getEventCode(),
								"emailTemplates/eventRegistryEmailTemplate.mail", 
								clientParams);
		
		List<String> chopParams = new ArrayList<>();
		chopParams.add(savedEr.getEventCode());
		chopParams.add(savedEr.getName() + " " + savedEr.getLastname() + " " + savedEr.getMothersname());
		chopParams.add(savedEr.getEmail());
		chopParams.add(savedEr.getPhone());
		chopParams.add(savedEr.getEventDesc());
		
		Boolean chopMail   = emailService.sendMail(bcc, 
								null, 
								"Datos del concursante, registro #CBM-2020-" + savedEr.getEventCode(), 
								"emailTemplates/eventRegisterEmailTemplate.mail", 
								chopParams);
		
		return clientMail && chopMail ? "fragments/frags :: registryDone" : "fragments/frags :: errors";
	}

}


