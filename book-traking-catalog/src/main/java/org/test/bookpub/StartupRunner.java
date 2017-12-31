package org.test.bookpub;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.test.bookpub.repository.BookRepository;

public class StartupRunner implements CommandLineRunner {

	protected final Log	   logger = LogFactory.getLog(getClass());

	@Autowired
	private BookRepository bookRepository;

	public void run(String... args) throws Exception {
		
		logger.info("Number of books: " + bookRepository.count());
	}
}
