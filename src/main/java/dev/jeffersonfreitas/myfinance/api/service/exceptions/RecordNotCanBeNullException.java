package dev.jeffersonfreitas.myfinance.api.service.exceptions;

public class RecordNotCanBeNullException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public RecordNotCanBeNullException(String msg) {
		super(msg);
	}

}
