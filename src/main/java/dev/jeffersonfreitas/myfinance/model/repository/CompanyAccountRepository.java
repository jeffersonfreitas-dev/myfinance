package dev.jeffersonfreitas.myfinance.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.jeffersonfreitas.myfinance.model.entity.Company;
import dev.jeffersonfreitas.myfinance.model.entity.CompanyAccount;

public interface CompanyAccountRepository extends JpaRepository<CompanyAccount, Long>{

	Optional<CompanyAccount> findByAccountAndCompany(String account, Company company);

	Optional<CompanyAccount> findByCodeIgnoreCase(String code);

}
