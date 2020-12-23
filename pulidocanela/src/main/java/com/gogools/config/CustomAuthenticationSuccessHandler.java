package com.gogools.config;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.gogools.beans.SessionUser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Autowired
	SessionUser sessionUser;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		

		handle(request, response, authentication);
		clearAuthenticationAttributes(request, (AuthUser) authentication.getPrincipal());
	}

	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

		String targetUrl = "/index";

		if (response.isCommitted()) {

			log.debug("===== Response has already been committed. Unable to redirect to " + targetUrl);
			return;
		}

		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	protected void clearAuthenticationAttributes(HttpServletRequest request, AuthUser user) {
		
		HttpSession session = request.getSession(false);
		
		if (session == null) {
			
			return;
		}
		
		sessionUser.setUser(user.getUser());
		sessionUser.setEmail(user.getUsername());
		
		log.debug("===== Session user: {} - {} ",sessionUser.getUser(), sessionUser.getEmail());
		
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

}
