package com.msen.demo.exceptions;

public class PriceException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public PriceException() {
		super();
	}
	public PriceException(String message) {
		super(message);
	}


}
