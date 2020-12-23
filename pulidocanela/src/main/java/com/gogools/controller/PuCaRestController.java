package com.gogools.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gogools.domain.User;
import com.gogools.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/data")
public class PuCaRestController {

	private UserService userservice;

	public PuCaRestController( UserService us ) {
	
		this.userservice = us;
	}

	@PostMapping(value = "/updateInvites")
	public ResponseEntity<?> saveInvites(@RequestBody List<User> users) {

		log.info("saveInvites(" + users.size() + ")");

		return new ResponseEntity<List<User>>( userservice.updateInvites(users), HttpStatus.OK);
	}
}
