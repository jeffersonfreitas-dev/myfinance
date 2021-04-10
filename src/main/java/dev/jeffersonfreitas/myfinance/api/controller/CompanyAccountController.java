package dev.jeffersonfreitas.myfinance.api.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.jeffersonfreitas.myfinance.api.service.BankAgenceService;
import dev.jeffersonfreitas.myfinance.api.service.CompanyAccountService;
import dev.jeffersonfreitas.myfinance.api.service.CompanyService;
import dev.jeffersonfreitas.myfinance.model.dto.CompanyAccountDTO;
import dev.jeffersonfreitas.myfinance.model.entity.BankAgence;
import dev.jeffersonfreitas.myfinance.model.entity.Company;
import dev.jeffersonfreitas.myfinance.model.entity.CompanyAccount;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/company/account")
@AllArgsConstructor
public class CompanyAccountController {

	private final CompanyAccountService service;
	private final CompanyService companyService;
	private final BankAgenceService bankAgenceService;
	
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CompanyAccount create(@RequestBody @Valid CompanyAccountDTO dto) {
		CompanyAccount companyAccount = convertDtoToEntity(dto);
		companyAccount = service.save(companyAccount);
		return companyAccount;
	}
	
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public CompanyAccount update(@PathVariable Long id, @Valid @RequestBody CompanyAccountDTO dto) {
		CompanyAccount account = service.findById(id);
		CompanyAccount convert = convertDtoToEntity(dto);
		account.setAccount(convert.getAccount());
		account.setBankAgence(convert.getBankAgence());
		account.setCode(convert.getCode());
		account.setCompany(convert.getCompany());
		return service.save(account);
	}

	
	@GetMapping("{id}")
	public CompanyAccount getAccount(@PathVariable Long id) {
		return service.findById(id);
	}
	
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
	
	
	
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Page<CompanyAccount> filter(@RequestBody CompanyAccountDTO dto, Pageable pageable){
		CompanyAccount filter = new CompanyAccount();
		
		if(dto.getCompany() != null) {
			Company company = companyService.findById(dto.getCompany());
			filter.setCompany(company);
		}
		
		if(dto.getBankAgence() != null) {
			BankAgence agence = bankAgenceService.findById(dto.getBankAgence());
			filter.setBankAgence(agence);
		}
		
		Page<CompanyAccount> result = service.filter(filter, pageable);
		return new PageImpl<>(result.getContent(), pageable, result.getTotalElements());
	}
	
	
	
	private CompanyAccount convertDtoToEntity(@Valid CompanyAccountDTO dto) {
		Company company = companyService.findById(dto.getCompany());
		BankAgence bankAgence = bankAgenceService.findById(dto.getBankAgence());
		return CompanyAccount.builder()
					.account(dto.getAccount())
					.bankAgence(bankAgence)
					.code(dto.getCode())
					.company(company)
					.build();
	}
	
}
