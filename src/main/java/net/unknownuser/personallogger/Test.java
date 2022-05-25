package net.unknownuser.personallogger;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Test {
	
	public static void main(String[] args) {
		ConsoleLogger logger = new ConsoleLogger();
//		FileLogger logger = FileLogger.getLogger();
		
		logger.log("Test1");
		logger.info("Test2");
		logger.debug("Test3");
		logger.warning("Test4");
		logger.error("Test4");
		
		logger.setTimezone(ZoneId.of("UTC"), true);
		logger.setDateFormat(DateTimeFormatter.ofPattern("yyyy"), true);
		logger.setANSIenabled(false);
		
		logger.info("new time zone");
		
	}
}
