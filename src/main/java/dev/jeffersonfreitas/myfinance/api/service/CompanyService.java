package dev.jeffersonfreitas.myfinance.api.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.jeffersonfreitas.myfinance.model.entity.Company;

public interface CompanyService {

	Company findById(Long id);

	Company save(Company company);

	void delete(Long id);

	List<Company> findAllOrderByName();

	Page<Company> filter(Company filter, Pageable pageable);

}
