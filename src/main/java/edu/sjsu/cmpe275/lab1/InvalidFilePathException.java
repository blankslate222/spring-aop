package edu.sjsu.cmpe275.lab1;

/**
 * @author Nikhil
 * 
 * Exception that is thrown when the path specified is not 
 * according the the format specified
 * 
 * Thrown before file not found exception since the path is
 * checked before the execution of all methods in file share service
 *
 */
public class InvalidFilePathException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidFilePathException() {
		
	}

	public InvalidFilePathException(String arg0) {
		super(arg0);
		
	}

}
