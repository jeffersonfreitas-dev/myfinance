package dev.jeffersonfreitas.myfinance.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import dev.jeffersonfreitas.myfinance.api.service.BankService;
import dev.jeffersonfreitas.myfinance.api.service.exceptions.BusinessException;
import dev.jeffersonfreitas.myfinance.model.dto.BankDTO;
import dev.jeffersonfreitas.myfinance.model.entity.Bank;
import dev.jeffersonfreitas.myfinance.model.repository.BankRespository;

@Service
public class BankServiceImpl implements BankService{

	@Autowired
	private BankRespository repository;
	
	@Override
	public Bank findById(Long id) {
		return repository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro não encontrado. ID:" + id));
	}

	
	@Override
	public List<Bank> findAllOrderByName() {
		return repository.findAllByOrderByNameAsc();
	}


	@Override
	public Bank save(Bank bank) {
		verifyIfRecordAlreadyExists(bank);
		return repository.save(bank);
	}


	@Override
	public void delete(Long id) {
		Bank bank = findById(id);
		repository.delete(bank);
	}


	@Override
	public Bank update(Long id, BankDTO dto) {
		Bank bank = findById(id);
		bank.setCode(dto.getCode());
		bank.setName(dto.getName());
		return repository.save(bank);
	}


	@Override
	public Page<Bank> filter(Bank filter, Pageable pageable) {
		Example<Bank> example = Example.of(filter, 
				ExampleMatcher
					.matching()
					.withIgnoreCase()
					.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));
		return repository.findAll(example, pageable);
	}
	
	
	@Override
	public Page<Bank> findAllByNameOrCodeContaining(String name, String code, Pageable pageable) {
		return repository.findAllByNameOrCodeContainingIgnoreCase(name, code, pageable);
	}	


	@Override
	public Bank findOne(Long id) {
		return repository.getOne(id);
	}

	
	@SuppressWarnings("unlikely-arg-type")
	private void verifyIfRecordAlreadyExists(Bank bank) {
		Optional<Bank> optional = repository.findByCodeOrNameIgnoreCase(bank.getCode(), bank.getName());
		
		if(optional.isPresent() && !optional.equals(bank)) {
			throw new BusinessException("Nome e/ou código do banco já cadastrado");
		}
	}

}

