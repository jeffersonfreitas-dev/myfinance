package dev.jeffersonfreitas.myfinance.api.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.jeffersonfreitas.myfinance.model.entity.BankAgence;

public interface BankAgenceService {

	BankAgence save(BankAgence agence);

	BankAgence findById(Long id);

	List<BankAgence> findAllOrderByAgence();

	void delete(Long id);

	Page<BankAgence> filter(BankAgence filter, Pageable pageable);

}
