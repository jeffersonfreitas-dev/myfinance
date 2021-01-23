package dev.jeffersonfreitas.myfinance.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import dev.jeffersonfreitas.myfinance.service.exceptions.ObjectNullException;

@ControllerAdvice
public class ControllExceptionHandler {
	

	
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<ErrorPadrao> handlerNotFound(RecordNotFoundException e){
		ErrorPadrao error = new ErrorPadrao(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	
	@ExceptionHandler(ObjectNullException.class)
	public ResponseEntity<ErrorPadrao> handlerObjectNull(ObjectNullException e){
		ErrorPadrao error = new ErrorPadrao(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorPadrao> handlerValidationModel(MethodArgumentNotValidException e){
		ErrorPadrao error = new ErrorPadrao(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);		
	}

}
