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
	
	/**
	 * return the name of this level.
	 * @return The name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the colour of this level.
	 * @return The current colour.
	 */
	public ANSICodes getColour() {
		return colour;
	}
	
	/**
	 * Changes the colour of this level.
	 * @param colour The new colour of this level.
	 */
	public void setColour(ANSICodes colour) {
		this.colour = colour;
	}
}
