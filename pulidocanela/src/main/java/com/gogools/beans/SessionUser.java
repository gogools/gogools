package com.gogools.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import lombok.Data;

@Data
@Component("sessionUser")
@Scope(value = WebApplicationContext.SCOPE_SESSION, 
		proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionUser {

	private String	user;
	private String	email;

}
