package dev.jeffersonfreitas.myfinance.api.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.jeffersonfreitas.myfinance.model.dto.BankDTO;
import dev.jeffersonfreitas.myfinance.model.entity.Bank;

public interface BankService {

	Bank findById(Long id);

	List<Bank> findAllOrderByName();

	Bank save(Bank bank);

	void delete(Long id);

	Bank update(Long id, BankDTO dto);

	Page<Bank> filter(Bank filter, Pageable pageable);
	
	Page<Bank> findAllByNameOrCodeContaining(String name, String code, Pageable pageable);

}
