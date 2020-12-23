package com.gogools.chop.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.gogools.sb.service.files.StorageProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class) // file management
public class ChopchopshopAdminApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(ChopchopshopAdminApplication.class, args);
	}

}
