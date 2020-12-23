package com.gogools.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.gogools.beans.SessionUser;
import com.gogools.domain.Comment;
import com.gogools.domain.User;
import com.gogools.enums.RoleEnum;
import com.gogools.forms.HailForm;
import com.gogools.service.CommentService;
import com.gogools.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class PuCaController {
	
	@Autowired
	SessionUser sessionUsr;
	
	private UserService userservice;
	private CommentService commservice;

	public PuCaController( UserService us, CommentService cs ) {
	
		this.userservice = us;
		this.commservice = cs;
	}
	
	@GetMapping("/")
	public String getHome() {
		
		return "redirect:/index";
	}
	
	@GetMapping("/rescue")
	public String rescue() {
		
		userservice.recueDatabase();
		
		return "redirect:/";
	}

	@GetMapping("/index")
	public String getInvitationPage(Model model) {
		
		HailForm hf = new HailForm();
		
		log.info("===== Session to index, user:{}, email:{}",  sessionUsr.getUser(), sessionUsr.getEmail());
		
		hf.setUsername( sessionUsr.getUser() );
		hf.setEmail( sessionUsr.getEmail() );
		
		model.addAttribute("hailForm", hf);
		
		return "index";
	}
	
	
	@PostMapping("/confirm")
	public String saveComment( @ModelAttribute HailForm hf ) {
		
		log.info("===== HailForm, user:{}, email:{}, comment:{}",  hf.getUsername(), hf.getEmail(), hf.getComment());
		
		User usr = userservice.getUserByEmail( hf.getEmail() );
		
		Comment c = new Comment();
		
		c.setUser(usr);
		c.setComment(hf.getComment());
		
		commservice.saveComment(c);
		
		return "fragments :: footer";
	}

	
	@GetMapping("/login")
	public String getLoginPage() {
		
		return "login";
	}
	
	
	@GetMapping(value = "/invitados")
	public String invites( Model model) {
		
		model.addAttribute("users", userservice.getAllUsers());
		
		return "invites";
	}
	
	@PostMapping(value = "/invitados")
	public String invites( @RequestParam("file") MultipartFile file, Model model) {
		
		log.info("===== File to upload: [{}], file is empty? {}", file.getOriginalFilename(), file.isEmpty());
		
		if( file.isEmpty() ) {
		
			model.addAttribute("errorMsg", "El archivo " + file.getOriginalFilename() + " esta vacio.");
			return "invites";
		}
		
		try {
			
			Workbook workbook = new XSSFWorkbook(file.getInputStream());
			
			Sheet sheet = workbook.getSheetAt(0);

			List<User> users = new ArrayList<>();
			
			log.info("===== Reading sheet name:{}, rows:{}", sheet.getSheetName(), sheet.getPhysicalNumberOfRows());
			
			for (Row row : sheet) {
				
				if( row.getCell(0) == null || row.getCell(1) == null ) {
					
					continue;
				}
				
				log.info("===== Registro[{}]: Nombre:{}, Email:{}", row.getRowNum(), row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue());
				
				User c = new User( row.getCell(0).getStringCellValue(), 
								   row.getCell(1).getStringCellValue(), 
								   RoleEnum.NORMAL_ROLE );
				
				users.add(c);
			}
			
			workbook.close();
			
			userservice.updateInvites(users);
			
			model.addAttribute("users", userservice.getAllUsers());
			model.addAttribute("msg", "Petición enviada");
			
		} catch (IOException e) {

			e.printStackTrace();
			model.addAttribute("errorMsg", "No pudimos procesar tu petición, por favor intenta más tarde.");
		}
		
		return "invites";
	}
}
