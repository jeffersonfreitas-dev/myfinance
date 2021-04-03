package dev.jeffersonfreitas.myfinance.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.jeffersonfreitas.myfinance.model.entity.BankAgence;

public interface BankAgenceRepository extends JpaRepository<BankAgence, Long>{

	Optional<BankAgence> findByAgenceIgnoreCaseAndBankId(String agence, Long bank);

	List<BankAgence> findAllByOrderByAgenceAsc();

}
