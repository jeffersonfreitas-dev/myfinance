package dev.jeffersonfreitas.myfinance.api.service.exceptions;

public class RecordNotCanBeDeletedException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public RecordNotCanBeDeletedException(String msg) {
		super(msg);
	}

}
