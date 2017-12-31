package org.test.bookpub;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookPubApplication {
	
	protected final Log	   logger = LogFactory.getLog(getClass());

	public static void main(String[] args) {

		SpringApplication.run(BookPubApplication.class, args);
	}

	@Bean
	public StartupRunner schedulerRunner() {

		return new StartupRunner();
	}

	@Autowired
	private DataSource ds;

	@Bean
	public CommandLineRunner lambdaRunner() {
		
		return (args) -> {
			
			logger.info("DataSource LAMBDA: " + ds.toString());
		};
	}
}
