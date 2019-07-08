package com.bridgelabz.exception;

public class LabelException extends RuntimeException {
	 
	
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public LabelException() {}

	public LabelException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "LabelException [message=" + message + "]";
	}
	
	

}
