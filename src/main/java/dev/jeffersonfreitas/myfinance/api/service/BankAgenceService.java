package dev.jeffersonfreitas.myfinance.api.service;

import java.util.List;

import dev.jeffersonfreitas.myfinance.model.entity.BankAgence;

public interface BankAgenceService {

	BankAgence save(BankAgence agence);

	BankAgence findById(Long id);

	List<BankAgence> findAllOrderByAgence();

	void delete(Long id);

}
