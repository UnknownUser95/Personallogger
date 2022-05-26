package net.unknownuser.personallogger;

class Log {
	protected Object data;
	protected LogLevel level;
	
	public Log(Object data, LogLevel level) {
		super();
		this.data = data;
		this.level = level;
	}

	public Object getData() {
		return data;
	}

	public LogLevel getLevel() {
		return level;
	}
}
