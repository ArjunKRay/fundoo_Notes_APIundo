package com.bridgelabz.exception;

public class NoteException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String message;

	
	public NoteException() {}
	
	public NoteException(String message) {
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
		return "NoteException [message=" + message + "]";
	}
	

}
