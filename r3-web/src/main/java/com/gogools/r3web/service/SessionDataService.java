package com.gogools.r3web.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gogools.r3web.domain.User;
import com.gogools.r3web.repository.UserRepository;
import com.gogools.r3web.session.SessionData;
import com.gogools.utils.ConsolePrinter;

@Service
public class SessionDataService {
	
	public static final Logger logger = LoggerFactory.getLogger(SessionDataService.class);

	@Autowired
	private SessionData		sessionData;

	@Autowired
	private UserRepository	userRepo;

	public void setSessionData(String userName, List<String> authorities) {

		User user = userRepo.findByUsername(userName);
		
		sessionData.setIdUser(user.getIduser());
		sessionData.setUserName(user.getUsername());
		sessionData.setUserIsActive(user.isIsactive());
		sessionData.setUserLastLogin(user.getLastlogin());
		sessionData.setIdClient(user.getClient().getIdclient());
		sessionData.setClientName(user.getClient().getName());
		sessionData.setEmail(user.getClient().getEmail());
		sessionData.setAuthorities(authorities);
		
		logger.info( ConsolePrinter.logHeader + "UserName: {}"		, sessionData.getUserName());
		logger.info( ConsolePrinter.logHeader + "Authorities: {}"	, ConsolePrinter.getPrettyJson(sessionData.getAuthorities()));
		logger.info( ConsolePrinter.logHeader + "IsActive: {}"		, ConsolePrinter.getPrettyJson(sessionData.getUserIsActive()));
		logger.info( ConsolePrinter.logHeader + "LastLogin: {}"	, ConsolePrinter.getPrettyJson(sessionData.getUserLastLogin()));
		logger.info( ConsolePrinter.logHeader + "Client ID: {}"	, ConsolePrinter.getPrettyJson(sessionData.getIdClient()));
		logger.info( ConsolePrinter.logHeader + "ClientName: {}"	, ConsolePrinter.getPrettyJson(sessionData.getClientName()));

	}
	
	public Date getSessionStarts() {
		
		return sessionData.getSessionStarts();
	}
	
	public Long	getIdClient() {
		
		return sessionData.getIdClient();
	}
	
	public Long	getIdUser() {
		
		return sessionData.getIdUser();
	}
	
	public Boolean getUserIsActive() {
		
		return sessionData.getUserIsActive();
	}
	
	public Date getUserLastLogin() {
		
		return sessionData.getUserLastLogin();
	}
	
	public String getClientName() {
		
		return sessionData.getClientName();
	}
	
	public String getUserName() {
		
		return sessionData.getUserName();
	}
	
	public List<String>	getAuthorities() {
		
		return sessionData.getAuthorities();
	}
	
	public String getEmail() {
		
		return sessionData.getEmail();
	}
}
