package dev.jeffersonfreitas.myfinance.api.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import dev.jeffersonfreitas.myfinance.api.service.AccountSubPlanService;
import dev.jeffersonfreitas.myfinance.api.service.exceptions.BusinessException;
import dev.jeffersonfreitas.myfinance.model.entity.AccountSubPlan;
import dev.jeffersonfreitas.myfinance.model.repository.AccountSubPlanRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountSubPlanServiceImpl implements AccountSubPlanService{
	
	private AccountSubPlanRepository repository;

	@Override
	public AccountSubPlan save(AccountSubPlan account) {
		vefifyIfRecordAlreadyExists(account);
		return repository.save(account);
	}
	
	
	@Override
	public AccountSubPlan findById(Long id) {
		return repository.findById(id).orElseThrow( 
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro não encontrado. ID:" + id));
	}
	
	
	
	@Override
	public void delete(Long id) {
		AccountSubPlan account = findById(id);
		repository.delete(account);
	}

	
	@Override
	public Page<AccountSubPlan> filter(AccountSubPlan filter, Pageable pageable) {
		Example<AccountSubPlan> result = Example.of(filter, 
				ExampleMatcher
					.matching()
					.withIgnoreCase()
					.withIgnoreNullValues()
					.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
				);
		return repository.findAll(result, pageable);
	}

	
	private void vefifyIfRecordAlreadyExists(AccountSubPlan account) {
		Optional<AccountSubPlan> opt = repository.findByNameIgnoreCaseAndAccountPlan(account.getName(), account.getAccountPlan());
		if(opt.isPresent() && !opt.get().equals(account)) {
			throw new BusinessException("Sub-Plano de conta já cadastrado com este nome e plano de conta");
		}
		
	}





}
