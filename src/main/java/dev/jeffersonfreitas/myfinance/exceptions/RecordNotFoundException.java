package dev.jeffersonfreitas.myfinance.exceptions;

public class RecordNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public RecordNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public RecordNotFoundException(String msg) {
		super(msg);
	}

}
