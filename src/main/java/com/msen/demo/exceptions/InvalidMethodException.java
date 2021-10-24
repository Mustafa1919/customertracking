package com.msen.demo.exceptions;

public class InvalidMethodException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public InvalidMethodException() {
		super();
	}
	public InvalidMethodException(String message) {
		super(message);
	}

}