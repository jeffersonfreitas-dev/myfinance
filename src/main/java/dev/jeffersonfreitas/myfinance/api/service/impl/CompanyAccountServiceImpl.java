package dev.jeffersonfreitas.myfinance.api.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import dev.jeffersonfreitas.myfinance.api.service.CompanyAccountService;
import dev.jeffersonfreitas.myfinance.api.service.exceptions.BusinessException;
import dev.jeffersonfreitas.myfinance.api.service.exceptions.RecordNotCanBeDeletedException;
import dev.jeffersonfreitas.myfinance.api.service.exceptions.RecordNotCanBeNullException;
import dev.jeffersonfreitas.myfinance.model.entity.CompanyAccount;
import dev.jeffersonfreitas.myfinance.model.repository.CompanyAccountRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CompanyAccountServiceImpl implements CompanyAccountService{
	
	private final CompanyAccountRepository repository;
	
	

	@Override
	public CompanyAccount save(CompanyAccount companyAccount) {
		if(companyAccount == null) {
			throw new RecordNotCanBeNullException("Não pode salvar um registro nulo.");
		}
		verifyIfRecordAlreadyRecorded(companyAccount);
		return repository.save(companyAccount);
	}
	


	@Override
	public CompanyAccount findById(Long id) {
		return repository.findById(id).orElseThrow( 
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro não encontrado. ID:" + id));
	}

	
	@Override
	public void delete(Long id) {
		CompanyAccount companyAccount = findById(id);
		try {
			repository.delete(companyAccount);
		}catch(Exception e) {
			throw new RecordNotCanBeDeletedException("Ocorreu um erro e o registro não pode ser deletado.");
		}		
	}	
	
	
	
	@Override
	public Page<CompanyAccount> filter(CompanyAccount filter, Pageable pageable) {
		Example<CompanyAccount> example = Example.of(filter, 
				ExampleMatcher
					.matching()
					.withIgnoreCase()
					.withIgnoreNullValues()
					.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)				
				);
		return repository.findAll(example, pageable);
	}
	
	
	
	private void verifyIfRecordAlreadyRecorded(CompanyAccount companyAccount) {
		Optional<CompanyAccount> opt = repository.findByAccountAndCompany(companyAccount.getAccount(), companyAccount.getCompany());
		if(opt.isPresent() && !opt.get().equals(companyAccount)) {
			throw new BusinessException("Já existe uma conta bancária cadastrada com este número para esta empresa");
		}
		
		Optional<CompanyAccount> opt2 = repository.findByCodeIgnoreCase(companyAccount.getCode());
		if(opt2.isPresent() && !opt2.get().equals(companyAccount)) {
			throw new BusinessException("Já existe uma conta bancária cadastrada com este código para esta empresa");
		}
	}

}
