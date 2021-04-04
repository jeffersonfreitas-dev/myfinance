package dev.jeffersonfreitas.myfinance.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.jeffersonfreitas.myfinance.model.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{

	Optional<Company> findByNameAndDocumentIgnoreCase(String name, String document);

	List<Company> findAllByOrderByName();

}
