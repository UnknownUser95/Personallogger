package net.unknownuser.personallogger;

import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public abstract class Logger {
	
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss:SS");
	private ZoneId timezone = ZoneId.of("UTC");
	
	protected PrintStream logStream;
	
	private boolean timeStampEnabled = true;
	
	// ---------- setters and getters ----------
	
	public void setTimezone(ZoneId zone, boolean logChange) {
		if(logChange) {
			internalLog(String.format("timezone changed from \"%s\" to \"%s\"", timezone, zone), LogLevel.INFO);
		}
		timezone = zone;
	}
	
	public void setTimezone(ZoneId zone) {
		setTimezone(zone, false);
	}
	
	public void setDateFormat(DateTimeFormatter format, boolean logChange) {
//		useless output
//		internalLog(String.format("timeformat changed from \"%s\" to \"%s\"", dtf, format), LogLevel.INFO);
		if(logChange) {
			internalLog("time format has been changed", LogLevel.INFO);
		}
		dtf = format;
	}
	
	public void setDateFormat(DateTimeFormatter format) {
		setDateFormat(format, false);
	}
	
	// ---------- constructors ----------
	
	protected Logger(PrintStream where) {
		super();
		timezone = ZoneId.systemDefault();
		logStream = where;
	}
	
	protected Logger(PrintStream where, ZoneId timezone) {
		super();
		logStream = where;
		this.timezone = timezone;
	}
	
	protected Logger(PrintStream logStream, ZoneId timezone, DateTimeFormatter format) {
		super();
		this.logStream = logStream;
		this.timezone = timezone;
		this.dtf = format;
	}
	
	// ---------- attribute changers ----------
	
	public void setTimeStampEnabled(boolean enabled) {
		timeStampEnabled = enabled;
	}
	
	// ---------- generic methods ----------
	
	public String getNow() {
		LocalDateTime now = LocalDateTime.now(timezone);
		return String.format("[%s]", dtf.format(now));
	}
	
	protected String getTimeStamp() {
		return (timeStampEnabled) ? getNow() + " " : "";
	}
	
	// ---------- logging methods ----------
	
	protected abstract void internalLog(Object o, LogLevel level);
	
	public void log(Object o, LogLevel level) {
		if(level == null) {
			return;
		}
		
		internalLog(o, level);
	}
	
	public void log(Object o) {
		internalLog(o, LogLevel.NONE);
	}
	
	public void info(Object o) {
		internalLog(o, LogLevel.INFO);
	}
	
	public void debug(Object o) {
		internalLog(o, LogLevel.DEBUG);
	}
	
	public void warning(Object o) {
		internalLog(o, LogLevel.WARNING);
	}
	
	public void error(Object o) {
		internalLog(o, LogLevel.ERROR);
	}
	
	public void none(Object o) {
		internalLog(o, LogLevel.NONE);
	}
}
