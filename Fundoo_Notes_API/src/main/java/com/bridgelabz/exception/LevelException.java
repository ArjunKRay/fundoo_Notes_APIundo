package com.bridgelabz.exception;

public class LevelException extends RuntimeException {
	 
	
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public LevelException() {}

	public LevelException(String message) {
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
		return "LevelException [message=" + message + "]";
	}
	
	

}
