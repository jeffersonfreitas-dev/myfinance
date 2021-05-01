package dev.jeffersonfreitas.myfinance.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import dev.jeffersonfreitas.myfinance.api.service.CompanyService;
import dev.jeffersonfreitas.myfinance.api.service.exceptions.BusinessException;
import dev.jeffersonfreitas.myfinance.api.service.exceptions.RecordNotCanBeDeletedException;
import dev.jeffersonfreitas.myfinance.api.service.exceptions.RecordNotCanBeNullException;
import dev.jeffersonfreitas.myfinance.model.entity.Company;
import dev.jeffersonfreitas.myfinance.model.repository.CompanyRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService{

	private final CompanyRepository repository;
	
	
	@Override
	public Company save(Company company) {
		if(company == null) {
			throw new RecordNotCanBeNullException("Não pode salvar um registro nulo.");
		}
		verifyIfRecordAlreadyExists(company);
		return repository.save(company);
	}
	


	@Override
	public Company findById(Long id) {
		return repository.findById(id).orElseThrow( 
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro não encontrado. ID:" + id));
	}


	@Override
	public void delete(Long id) {
		Company company = findById(id);
		try {
			repository.delete(company);
		}catch(Exception e) {
			throw new RecordNotCanBeDeletedException("Ocorreu um erro e o registro não pode ser deletado.");
		}
	}
	
	

	@Override
	public List<Company> findAllOrderByName() {
		return repository.findAllByOrderByName();
	}
	
	
	@Override
	public Page<Company> filter(Company filter, Pageable pageable) {
		Example<Company> result = Example.of(filter, 
				ExampleMatcher
					.matching()
					.withIgnoreCase()
					.withIncludeNullValues()
					.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
				);
		return repository.findAll(result, pageable);
	}
	
	
	
	private void verifyIfRecordAlreadyExists(Company company) {
		Optional<Company> opt = repository.findByNameAndDocumentIgnoreCase(company.getName(), company.getDocument());
		if(opt.isPresent() && !opt.get().equals(company)) {
			throw new BusinessException("Já existe uma empresa com este nome e documento cadastrado");
		}
	}
}
