package com.gogools.chop.cfg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gogools.chop.interceptor.MenuInterceptor;

@Configuration
public class WebAppConfig implements WebMvcConfigurer {
	
	@Autowired
	MenuInterceptor menuInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		WebMvcConfigurer.super.addInterceptors(registry);
		
		registry.addInterceptor( menuInterceptor );//.addPathPatterns("/**");// Intercept all requests
	}
}
