package dev.jeffersonfreitas.myfinance.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.jeffersonfreitas.myfinance.model.entity.Bank;

public interface BankRespository extends JpaRepository<Bank, Long>{

	List<Bank> findAllByOrderByNameAsc();

	Optional<Bank> findByCodeOrNameIgnoreCase(String code, String name);

}
