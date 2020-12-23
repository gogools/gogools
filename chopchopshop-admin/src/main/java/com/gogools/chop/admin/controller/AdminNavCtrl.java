package com.gogools.chop.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gogools.chop.admin.service.IBkendClientService;
import com.gogools.chop.dto.ProductDTO;
import com.gogools.enums.Emj;
import com.gogools.rest.RestResponseDTO;
import com.gogools.sb.service.files.IStorageService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AdminNavCtrl {
	
	@Autowired
	private IBkendClientService rest;
	
	@Autowired
	private IStorageService storage;
	
//	@GetMapping("/index")
//	public String getIndex( ModelMap model) {
//		
//		return "index";
//	} 
	
	@GetMapping("/")
	public ModelAndView getRoot( ModelMap model) {
		
		return new ModelAndView("redirect:/catalogAdmin", model);
	}
	
	@GetMapping("/catalogAdmin")
	public String getCatalogAdmin(Model model) {
		
		log.info(Emj.INFO + " getCatalogAdmin ");
		
		RestResponseDTO<List<ProductDTO>> dto = rest.getAllProducts();
		
		model.addAttribute("dto", dto);
		
		return "catalogAdmin";
	}

	@PostMapping("/catalogAdmin")
	public String updateCatalog( @RequestParam("excel") MultipartFile excel, Model model) {
		
		log.info(Emj.INFO + " updateCatalog >  File to upload: [{}], file is empty? {}", excel.getOriginalFilename(), excel.isEmpty());
		
		if( excel.isEmpty() ) {
			
			model.addAttribute("errorMsg", "El archivo " + excel.getOriginalFilename() + " esta vacio.");
			return "catalogAdmin";
		}
		
		String filename= StringUtils.cleanPath(excel.getOriginalFilename());
		
		storage.store(excel); 
		
		RestResponseDTO<List<ProductDTO>> dto = rest.bulkProducts(storage.loadAsResource(filename));
		
		storage.deleteFile(filename);
		
		model.addAttribute("dto", dto);
		
		return "catalogAdmin";
	}
}
