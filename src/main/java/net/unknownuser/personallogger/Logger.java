package net.unknownuser.personallogger;

import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * A class for generic loggers holding the basic logging methods.
 */
public abstract class Logger {
	
	protected DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss:SS");
	protected ZoneId timezone = ZoneId.of("UTC");
	
	protected PrintStream logStream;
	
	private boolean timeStampEnabled = true;
	
	// -------------------- setters and getters --------------------
	
	/**
	 * Changes the time zone of this logger to the specified one. The message for the change of the
	 * time zone can be disabled.
	 * 
	 * @param zone      The new time zone of this logger.
	 * @param logChange Whether a message for the change should be logged.
	 */
	public void setTimezone(ZoneId zone, boolean logChange) {
		if(logChange) {
			internalLog(String.format("timezone changed from \"%s\" to \"%s\"", timezone, zone), LogLevel.INFO);
		}
		timezone = zone;
	}
	
	/**
	 * Changes the time zone of this logger and logs a message of the change.
	 * 
	 * @param zone The new time zone of this logger.
	 */
	public void setTimezone(ZoneId zone) {
		setTimezone(zone, true);
	}
	
	/**
	 * Changes the time format of this logger to the specified one. The message for the change of the
	 * time zone can be disabled.
	 * 
	 * @param format    The new time format of this logger.
	 * @param logChange Whether a message for the change should be logged.
	 */
	public void setDateFormat(DateTimeFormatter format, boolean logChange) {
//		useless output
//		internalLog(String.format("timeformat changed from \"%s\" to \"%s\"", dtf, format), LogLevel.INFO);
		if(logChange) {
			internalLog("time format has been changed", LogLevel.INFO);
		}
		dtf = format;
	}
	
	/**
	 * Changes the time format of this logger and logs a message of the change.
	 * 
	 * @param zone The new time zone of this logger.
	 */
	public void setDateFormat(DateTimeFormatter format) {
		setDateFormat(format, true);
	}
	
	// -------------------- constructors --------------------
	
	/**
	 * Creates a logger, which sends all logs to the specified PrintStream.
	 * 
	 * @param where The PrintStream all logs are written to.
	 */
	protected Logger(PrintStream where) {
		super();
		timezone = ZoneId.systemDefault();
		logStream = where;
	}
	
	/**
	 * Creates a logger, which sends all logs to the specified PrintStream. Changes the time zone to
	 * the specified one.
	 * 
	 * @param where    The PrintStream all logs are written to.
	 * @param timezone The time zone of this logger.
	 */
	protected Logger(PrintStream where, ZoneId timezone) {
		super();
		logStream = where;
		setTimezone(timezone, false);
	}
	
	/**
	 * Creates a logger, which sends all logs to the specified PrintStream. Changes the time zone to
	 * the specified one.
	 * 
	 * @param where    The PrintStream all logs are written to.
	 * @param timezone The time zone of this logger.
	 * @param format   The time format of this logger.
	 */
	protected Logger(PrintStream logStream, ZoneId timezone, DateTimeFormatter format) {
		super();
		this.logStream = logStream;
		setTimezone(timezone, false);
		setDateFormat(format, false);
	}
	
	// -------------------- attribute changers --------------------
	
	/**
	 * Changes whether this logger should include time stamps in its logs.
	 * 
	 * @param enabled Whether time stamps should be included.
	 */
	public void setTimeStampEnabled(boolean enabled) {
		timeStampEnabled = enabled;
	}
	
	// -------------------- generic methods --------------------
	
	/**
	 * Returns the current time, formatted with this loggers time format.
	 * 
	 * @return The formatted current time.
	 */
	public String getNow() {
		LocalDateTime now = LocalDateTime.now(timezone);
		return String.format("[%s]", dtf.format(now));
	}
	
	/**
	 * Gets the time stamp of this logger. Essentially {@link #getNow()}, but with slight formatting.
	 * 
	 * @return The formatted current time.
	 */
	protected String getTimeStamp() {
		return (timeStampEnabled) ? getNow() + " " : "";
	}
	
	// -------------------- logging methods --------------------
	
	/**
	 * The method defining how exactly logs are handled. This method should implement
	 * logStream.println in some way.
	 * 
	 * @param o     The object to be logged.
	 * @param level The level of the log.
	 */
	protected abstract void internalLog(Object o, LogLevel level);
	
	/**
	 * Generic log method for specifying the level on its own.
	 * 
	 * @param o     The object to be logged.
	 * @param level The level of the log.
	 */
	public void log(Object o, LogLevel level) {
		if(level == null) {
			return;
		}
		
		internalLog(o, level);
	}
	
	/**
	 * Very generic log method. Essentially the same as {@link #none(Object)}.
	 * 
	 * @param o The object to be logged.
	 */
	public void log(Object o) {
		internalLog(o, LogLevel.NONE);
	}
	
	/**
	 * A log with the INFO level.
	 * 
	 * @param o The object to be logged.
	 */
	public void info(Object o) {
		internalLog(o, LogLevel.INFO);
	}
	
	/**
	 * A log with the DEBUG level.
	 * 
	 * @param o The object to be logged.
	 */
	public void debug(Object o) {
		internalLog(o, LogLevel.DEBUG);
	}
	
	/**
	 * A log with the WARNING level.
	 * 
	 * @param o The object to be logged.
	 */
	public void warning(Object o) {
		internalLog(o, LogLevel.WARNING);
	}
	
	/**
	 * A log with the ERROR level.
	 * 
	 * @param o The object to be logged.
	 */
	public void error(Object o) {
		internalLog(o, LogLevel.ERROR);
	}
	
	/**
	 * A log with the NONE level.
	 * 
	 * @param o The object to be logged.
	 */
	public void none(Object o) {
		internalLog(o, LogLevel.NONE);
	}
}
