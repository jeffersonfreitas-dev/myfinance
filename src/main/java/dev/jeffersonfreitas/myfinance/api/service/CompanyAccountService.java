package dev.jeffersonfreitas.myfinance.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.jeffersonfreitas.myfinance.model.entity.CompanyAccount;

public interface CompanyAccountService {

	CompanyAccount findById(Long id);

	CompanyAccount save(CompanyAccount companyAccount);

	void delete(Long id);

	Page<CompanyAccount> filter(CompanyAccount filter, Pageable pageable);

}
