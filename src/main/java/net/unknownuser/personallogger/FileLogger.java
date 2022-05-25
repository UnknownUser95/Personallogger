package net.unknownuser.personallogger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class FileLogger extends Logger {
	
	// ---------- constructors ----------
	
	private FileLogger(PrintStream stream) {
		super(stream);
	}
	
	// ---------- get methods for loggers ----------
	
	public static FileLogger getLogger(File logFile) {
		FileLogger logger = null;
		boolean appendMessage = logFile.exists();
		
		try {
			logger = new FileLogger(new PrintStream(new FileOutputStream(logFile, true)));
			
			if(appendMessage) {
				logger.none(null);
				logger.info("log file already existed, new logs are appended");
			}
		} catch(IOException exc) {
			System.err.println("error while creating logger\n" + exc.getMessage());
		}
		
		return logger;
	}
	
	public static FileLogger getLogger() {
		return getLogger(new File("log.log"));
	}
	
	public static FileLogger getLogger(File logFile, ZoneId zone) {
		if(!logFile.isFile()) {
			return null;
		}
		
		FileLogger logger = getLogger(logFile);
		
		if(logger != null) {
			logger.setTimezone(zone);
		}
		
		return logger;
	}
	
	public static FileLogger getLogger(File logFile, ZoneId zone, DateTimeFormatter format) {
		if(!logFile.isFile()) {
			return null;
		}
		
		FileLogger logger = getLogger(logFile);
		
		if(logger != null) {
			logger.setTimezone(zone);
			logger.setDateFormat(format);
		}
		
		return logger;
	}
	
	
	
	@Override
	protected void internalLog(Object o, LogLevel level) {
		if(o == null) {
			logStream.println();
			return;
		}
		
		String name = (level == LogLevel.NONE) ? "" : level.getName() + ": ";
		
		logStream.println(getTimeStamp() + name + o.toString());
	}
}
