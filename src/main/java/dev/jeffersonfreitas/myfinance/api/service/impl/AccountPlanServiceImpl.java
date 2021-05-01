package dev.jeffersonfreitas.myfinance.api.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import dev.jeffersonfreitas.myfinance.api.service.AccountPlanService;
import dev.jeffersonfreitas.myfinance.api.service.exceptions.BusinessException;
import dev.jeffersonfreitas.myfinance.api.service.exceptions.RecordNotCanBeDeletedException;
import dev.jeffersonfreitas.myfinance.api.service.exceptions.RecordNotCanBeNullException;
import dev.jeffersonfreitas.myfinance.model.entity.AccountPlan;
import dev.jeffersonfreitas.myfinance.model.repository.AccountPlanRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountPlanServiceImpl implements AccountPlanService{

	private final AccountPlanRepository repository;
	

	@Override
	public AccountPlan save(AccountPlan accountPlan) {
		if(accountPlan == null) {
			throw new RecordNotCanBeNullException("Não pode salvar um registro nulo.");
		}
		verifyIfRecordAlreadySaved(accountPlan);
		return repository.save(accountPlan);
	}



	@Override
	public AccountPlan findById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro não encontrado. ID:" + id));
	}
	
	
	@Override
	public void delete(Long id) {
		AccountPlan plan = findById(id);
		try {
			repository.delete(plan);
		}catch(Exception e) {
			throw new RecordNotCanBeDeletedException("Ocorreu um erro e o registro não pode ser deletado.");
		}
	}

	
	@Override
	public Page<AccountPlan> filter(AccountPlan plan, Pageable pageable) {
		Example<AccountPlan> filter = Example.of(plan, 
					ExampleMatcher
					.matching()
					.withIgnoreCase()
					.withIgnoreNullValues()
					.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
				);
		return repository.findAll(filter, pageable);
	}
	
	
	private void verifyIfRecordAlreadySaved(AccountPlan accountPlan) {
		Optional<AccountPlan> opt = repository.findByNameIgnoreCase(accountPlan.getName());
		if(opt.isPresent() && !opt.get().equals(accountPlan)) {
			throw new BusinessException("Já existe um plano de conta com este nome cadastrado.");
		}
	}	

}
