package dev.jeffersonfreitas.myfinance.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import dev.jeffersonfreitas.myfinance.model.entity.Bank;

public interface BankRepository extends JpaRepository<Bank, Long>{

	List<Bank> findAllByOrderByNameAsc();

	Optional<Bank> findByCodeOrNameIgnoreCase(String code, String name);

	Page<Bank> findAllByNameOrCodeContainingIgnoreCase(String name, String code, Pageable pageable);

}
