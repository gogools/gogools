package com.gogools.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {


	@Override
	public void onAuthenticationFailure(HttpServletRequest request, 
										HttpServletResponse response, 
										AuthenticationException exception) throws IOException, ServletException {

		response.setStatus(HttpStatus.UNAUTHORIZED.value());

		log.error("=== AUTH EXCEPTION msg: \n" + exception.getMessage());
		
		HttpSession session = request.getSession();
        session.setAttribute("loginError", exception.getMessage().equals("Bad credentials") ? "usuario o password incorrectos" : "intenta de nuevo más tarde");
        
		response.sendRedirect("/login?error=true");
	}
}