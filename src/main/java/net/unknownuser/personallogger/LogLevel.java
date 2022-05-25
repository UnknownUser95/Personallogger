package net.unknownuser.personallogger;

import net.unknownuser.ansi.ANSICodes;

enum LogLevel {
	INFO("INFO", ANSICodes.COLOUR_FOREGROUND_BRIGHT_GREEN),
	DEBUG("DEBUG", ANSICodes.COLOUR_FOREGROUND_BRIGHT_CYAN),
	WARNING("WARNING", ANSICodes.COLOUR_FOREGROUND_BRIGHT_YELLOW),
	ERROR("ERROR", ANSICodes.COLOUR_FOREGROUND_BRIGHT_RED),
	NONE("", ANSICodes.ALL_RESET);
	
	private String name;
	private ANSICodes colour;
	
	private LogLevel(String name, ANSICodes colour) {
		this.name = name;
		this.colour = colour;
	}
	
	public String getName() {
		return name;
	}
	
	public ANSICodes getColour() {
		return colour;
	}
	
	public void setColour(ANSICodes colour) {
		this.colour = colour;
	}
	
	public String colourName() {
		if(this == NONE) {
			return "";
		}
		
		return ANSICodes.colourString(name + ": ", colour);
	}
}
