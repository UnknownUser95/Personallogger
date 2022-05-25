package net.unknownuser.personallogger;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import net.unknownuser.ansi.ANSICodes;

public class ConsoleLogger extends Logger {
	
	private boolean ansiEnabled = true;
	
	// ---------- constructors ----------
	
	public ConsoleLogger() {
		super(System.out);
	}
	
	public ConsoleLogger(ZoneId timezone) {
		super(System.out, timezone);
	}
	
	public ConsoleLogger(ZoneId timezone, DateTimeFormatter format) {
		super(System.out, timezone, format);
	}
	
	// ---------- overwrite ----------
	
	@Override
	protected void internalLog(Object o, LogLevel level) {
		if(o == null) {
			logStream.println();
			return;
		}
		
		String name = (level == LogLevel.NONE) ? "" : level.getName() + ": ";
		if(ansiEnabled) {
			logStream.println(ANSICodes.colourString(getTimeStamp() + name + o.toString(), level.getColour()));
		} else {
			logStream.println(getTimeStamp() + name + o.toString());
		}
	}
	
	// ---------- other ----------
	
	public void setANSIenabled(boolean enabled) {
		ansiEnabled = enabled;
	}
}
