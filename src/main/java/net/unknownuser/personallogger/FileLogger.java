package net.unknownuser.personallogger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * A logger for writing logs into a file.
 */
public class FileLogger extends Logger {
	
	// -------------------- constructors --------------------
	
	/**
	 * Creates a FileLogger instance writing to the specified output.
	 * 
	 * @param stream The output where all logs are written to.
	 */
	private FileLogger(PrintStream stream) {
		super(stream);
	}
	
	// -------------------- get methods for loggers --------------------
	
	/**
	 * Creates a logger writing logs into a "log.log" file. It will be created automatically if it
	 * does not exist.
	 * 
	 * @return The logger or {@code null} if an error occurred.
	 */
	public static FileLogger getLogger() {
		return getLogger(new File("log.log"));
	}
	
	/**
	 * Creates a logger writing into the specified file.
	 * 
	 * @param logFile The file where the logs are written into.
	 * @return The logger or {@code null} if an error occurred.
	 */
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
	
	/**
	 * Creates a logger writing logs into the specified file. Changes the time zone to the specified
	 * one.
	 * 
	 * @param logFile The file where all logs are written into.
	 * @param zone    The time zone of the logger
	 * @return The logger or {@code null} if an error occurred.
	 */
	public static FileLogger getLogger(File logFile, ZoneId zone) {
		if(!logFile.isFile()) {
			return null;
		}
		
		FileLogger logger = getLogger(logFile);
		
		if(logger != null) {
			logger.setTimezone(zone, false);
		}
		
		return logger;
	}
	
	/**
	 * Creates a logger writing logs into the specified file. Changes the time zone and format to the
	 * specified one.
	 * 
	 * @param logFile The file where all logs are written into.
	 * @param zone    The time zone of the logger
	 * @param format  The format of the time stamps.
	 * @return The logger or {@code null} if an error occurred.
	 */
	public static FileLogger getLogger(File logFile, ZoneId zone, DateTimeFormatter format) {
		if(!logFile.isFile()) {
			return null;
		}
		
		FileLogger logger = getLogger(logFile);
		
		if(logger != null) {
			logger.setTimezone(zone, false);
			logger.setDateFormat(format, false);
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
