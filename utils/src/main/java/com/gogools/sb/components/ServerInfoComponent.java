package com.gogools.sb.components;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.gogools.enums.Emj;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ServerInfoComponent implements ApplicationRunner {

	@Value("${spring.profiles.active}")
	String env;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		log.info(Emj.COMPUTER + " === SERVER INFO === " + Emj.COMPUTER );
		log.info(Emj.COMPUTER + " Application started with option names : {}", args.getOptionNames());
		log.info(Emj.COMPUTER + " Environment: {}", env);
	}
}