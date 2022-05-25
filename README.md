# Overview

Personallogger is a logging library wriiten in Java. It was made for fun, not for a particular use. It has inbuilt logging capability for logging in a console and in a file.

The logger uses a PrintStream as the underlying object to print data. Any printing issues should come from there.

The time stamp of each logger as well as the colour of each level can be customized or disabled.

To log anything either call the log method and specify the level or call the method named after a level. For all levels, see [log levels](#Log Levels) below. The toString() method is called on the given object.

### ConsoleLogger

This is the logger for logging in a console. It uses ANSI escape codes for colour by default, but this can be disabled.

The colours can also be changed on a per level basis if you find the default ones unfitting.

### FileLogger

Since the creating of the logger is more complex than the ConsoleLogger normal instancing is not possible. Use the getLogger methods instead.

Writes the logs to a specified file (or to "log.log" in case of the getLogger() method).

If a specified file already exists, a notice will be logged after which the new logs will be appended.

### Log Levels

There are five levels:
- NONE
- INFO
- DEBUG
- WARNING
- ERROR

You can log with them either with the log method and specifying the level or with the method for each one.

Note that the log(Object) method logs with the NONE level and is the same as the none(Object) method.

As said before, the colours of each level can be customized. The colours use codes from my [ANSICodes](https://github.com/UnknownUser95/java-ansi) library.