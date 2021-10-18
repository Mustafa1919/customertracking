package com.msen.demo.exceptions;

public class CustomerBalanceUpdateException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public CustomerBalanceUpdateException() {
		super();
	}
	public CustomerBalanceUpdateException(String message) {
		super(message);
	}

}
