package net.unknownuser.personallogger;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import net.unknownuser.ansi.ANSICodes;

/**
 * A class for writing logs into the console.
 */
public class ConsoleLogger extends Logger {
	
	private boolean ansiEnabled = true;
	
	// -------------------- constructors --------------------
	
	/**
	 * Creates a logger printing into the console.
	 */
	public ConsoleLogger() {
		super(System.out);
	}
	
	/**
	 * Creates a logger printing into the console. Changes the time zone to the specified one.
	 * @param timezone The time zone of the logger.
	 */
	public ConsoleLogger(ZoneId timezone) {
		super(System.out, timezone);
	}
	
	/**
	 * Creates a logger printing into the console. Changes the time zone and format to the specified one.
	 * @param timezone The time zone of the logger.
	 * @param format The time format of the logger.
	 */
	public ConsoleLogger(ZoneId timezone, DateTimeFormatter format) {
		super(System.out, timezone, format);
	}
	
	// -------------------- overwrite --------------------
	
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
	
	// -------------------- other --------------------
	
	/**
	 * Changes whether the logs should be coloured.
	 * @param enabled Whether the logs should have colour.
	 * @see LogLevel#setColour(ANSICodes)
	 */
	public void setANSIenabled(boolean enabled) {
		ansiEnabled = enabled;
	}
}
