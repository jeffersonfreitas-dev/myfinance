package dev.jeffersonfreitas.myfinance.service.exceptions;

public class ObjectNullException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ObjectNullException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ObjectNullException(String msg) {
		super(msg);
	}

}
