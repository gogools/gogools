package com.gogools.r3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class R3Application {

	public static void main(String[] args) {
		
		SpringApplication.run(R3Application.class, args);
	}
	
	@Bean
	public ServerInfoSR schedulerRunner() {
		
	  return new ServerInfoSR();
	}
}
