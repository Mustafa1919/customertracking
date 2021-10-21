package com.msen.demo.exceptions;

public class ActivityNotFound extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ActivityNotFound() {
		super();
	}
	public ActivityNotFound(String message) {
		super(message);
	}
}
