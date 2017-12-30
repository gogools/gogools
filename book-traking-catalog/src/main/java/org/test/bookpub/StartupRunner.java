package org.test.bookpub;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class StartupRunner implements CommandLineRunner {

	protected final Log	logger = LogFactory.getLog(getClass());

	@Autowired
	private DataSource	ds;

	@Override
	public void run(String... args) throws Exception {
		
		logger.info("DataSource: " + ds.toString());
	}
}
