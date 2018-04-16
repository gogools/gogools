package com.gogools.r3;

import java.time.ZoneId;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;

import com.gogools.utils.ConsolePrinter;
import com.gogools.utils.DateUtil;

public class ServerInfoSR implements CommandLineRunner {

	protected final Log logger = LogFactory.getLog(ServerInfoSR.class);

	@Override
	public void run(String... args) throws Exception {
		
		ConsolePrinter.obj2Console("Date (Mex CDMX -06:00)", DateUtil.getCurrentMexDateTime());
		ConsolePrinter.obj2Console("Date ( SERVER )", DateUtil.getCurrentDateAtZone(ZoneId.systemDefault()));
	}
}