package com.medical.exception;

public class HospitalNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public HospitalNotFoundException(String message) {
		super(message);
	}
}
