package com.gogools.chop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.gogools.enums.Emj;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class ChopchopshopBkendApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(ChopchopshopBkendApplication.class, args);
	}
	
	@Bean
    public ApplicationRunner applicationRunner(@Value("${spring.profiles.active}") String env) {
        return args -> {
        	log.info(Emj.COMPUTER + " === SERVER INFO === " + Emj.COMPUTER );
    		log.info(Emj.COMPUTER + " Application started with option names : {}", args.getOptionNames());
			log.info(Emj.COMPUTER + " Environment: {}", env);
		};
    }
}
