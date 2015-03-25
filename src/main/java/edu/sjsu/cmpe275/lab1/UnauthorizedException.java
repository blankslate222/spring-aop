package edu.sjsu.cmpe275.lab1;

/**
 * @author Nikhil
 * 
 * Custom exception which is thrown if a user
 * performs an unauthorized operation
 *
 */
public class UnauthorizedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnauthorizedException() {
		
	}

	public UnauthorizedException(String arg0) {
		super(arg0);
		
	}

}
