package dev.jeffersonfreitas.myfinance.api.exceptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.server.ResponseStatusException;

import dev.jeffersonfreitas.myfinance.api.service.exceptions.BusinessException;
import dev.jeffersonfreitas.myfinance.api.service.exceptions.RecordNotCanBeDeletedException;
import dev.jeffersonfreitas.myfinance.api.service.exceptions.RecordNotCanBeNullException;

public class ApiErrors {

	private List<String> errors;
	
	public ApiErrors(BindingResult ex) {
		this.errors = new ArrayList<>();
		ex.getAllErrors().forEach(e -> this.errors.add(e.getDefaultMessage()));
	}
	
	
	public ApiErrors(ResponseStatusException ex) {
		this.errors = Arrays.asList(ex.getReason());
	}
	
	
	public ApiErrors(BusinessException ex) {
		this.errors = Arrays.asList(ex.getMessage());
	}
	

	public ApiErrors(DataIntegrityViolationException ex) {
		this.errors = Arrays.asList("Erro de integridade chave estrangeira", ex.getRootCause().getMessage());
	}


	public ApiErrors(RecordNotCanBeDeletedException ex) {
		this.errors = Arrays.asList(ex.getMessage());
	}


	public ApiErrors(RecordNotCanBeNullException ex) {
		this.errors = Arrays.asList(ex.getMessage());
	}


	public List<String> getErrors() {
		return errors;
	}
}
