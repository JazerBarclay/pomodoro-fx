package dev.jazer.pomodoroFX;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Debug {

	/**
	 * Prints info to the console the current time, parent class, message and exception<br><br>
	 * If an exception is defined, the output will be set in red with system.err
	 * 
	 * @param parent
	 * @param message
	 * @param exception
	 */
	public static void trace(Object parent, String message, Exception exception) {
		trace("INFO", parent, message, exception);
	}

	/**
	 * Prints to the console the current time, parent class, message and exception<br><br>
	 * If an exception is defined, the output will be set in red with system.err
	 * 
	 * @param type
	 * @param parent
	 * @param message
	 * @param exception
	 */
	public static void trace(String type, Object parent, String message, Exception exception) {
		String text = new SimpleDateFormat("HH:mm:ss (SSS)").format(new Date()) + " : " + type + " : " + parent.getClass().getSimpleName() + " : " + message;
		if (exception == null) {
			System.out.println(text);
		} else {
			System.err.println(text);
			exception.printStackTrace();
		}
	}
	
	/**
	 * Synchronously prints an information message to the console
	 * @param parent - Object calling this method
	 * @param message - Message to be printed
	 */
	public static synchronized void info(Object parent, String message) {
		trace("INFO", parent, message, null);
	}

	
	/**
	 * Synchronously prints a warning message to the console
	 * @param parent - Object calling this method
	 * @param message - Message to be printed
	 */
	public static synchronized void warn(Object parent, String message) {
		trace("WARN", parent, message, null);
	}

	
	/**
	 * Synchronously prints an error with exception to the console
	 * @param parent - Object calling this method
	 * @param message - Message to be printed
	 * @param exeption - Exception
	 */
	public static synchronized void error(Object parent, String message, Exception exeption) {
		trace("ERROR", parent, message, exeption);
	}
	
	
}
