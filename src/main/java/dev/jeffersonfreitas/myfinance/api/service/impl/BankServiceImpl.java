package dev.jeffersonfreitas.myfinance.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import dev.jeffersonfreitas.myfinance.api.service.BankService;
import dev.jeffersonfreitas.myfinance.api.service.exceptions.BusinessException;
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


	@SuppressWarnings("unlikely-arg-type")
	private void verifyIfRecordAlreadyExists(Bank bank) {
		Optional<Bank> optional = repository.findByCodeOrNameIgnoreCase(bank.getCode(), bank.getName());
		
		if(optional.isPresent() && !optional.equals(bank)) {
			throw new BusinessException("Nome e/ou código do banco já cadastrado");
		}
		
	}

}
