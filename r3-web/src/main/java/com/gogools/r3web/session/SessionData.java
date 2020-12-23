package com.gogools.r3web.session;

import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionData {

	private Date			sessionStarts	= new Date();
	private Long			idClient;
	private Long			idUser;
	private Boolean		userIsActive;
	private Date			userLastLogin;
	private String		clientName;
	private String		userName;
	private String		email;
	private List<String>	authorities;
	
	//TODO add currency
	//TODO add access cost 
}
