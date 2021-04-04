package dev.jeffersonfreitas.myfinance.api.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import dev.jeffersonfreitas.myfinance.api.service.exceptions.BusinessException;

@RestControllerAdvice
public class ApplicationAdviceException{


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity hanbleResponseStatusException(ResponseStatusException ex) {
		return new ResponseEntity(new ApiErrors(ex), ex.getStatus());
	}
	
	
	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleBusinessException(BusinessException ex) {
		return new ApiErrors(ex);
	}
	

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleBindingResult (MethodArgumentNotValidException ex) {
		BindingResult bindingResult = ex.getBindingResult();
		return new ApiErrors(bindingResult);
	}
	
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleDataIntegrityViolationException (DataIntegrityViolationException ex) {
		return new ApiErrors(ex);
		
	}
}
