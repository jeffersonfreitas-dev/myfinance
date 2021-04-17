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

import dev.jeffersonfreitas.myfinance.api.service.AccountPlanService;
import dev.jeffersonfreitas.myfinance.model.dto.AccountPlanDTO;
import dev.jeffersonfreitas.myfinance.model.entity.AccountPlan;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/accountplan")
@AllArgsConstructor
public class AccountPlanController {
	
	private final AccountPlanService service;
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AccountPlan create(@RequestBody @Valid AccountPlanDTO dto) {
		AccountPlan accountPlan = convertRecord(dto);
		accountPlan = service.save(accountPlan);
		return accountPlan;
	}
	
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
	
	
	@PutMapping("{id}")
	public AccountPlan update(@PathVariable Long id, @RequestBody AccountPlanDTO dto) {
		AccountPlan accountPlan = service.findById(id);
		accountPlan.setCredit(dto.isCredit());
		accountPlan.setName(dto.getName());
		return service.save(accountPlan);
	}


	@GetMapping("{id}")
	public AccountPlan getAccount(@PathVariable Long id) {
		return service.findById(id);
	}
	
	
	@GetMapping
	public Page<AccountPlan> filter(@RequestBody AccountPlanDTO dto, Pageable pageable){
		AccountPlan plan = convertRecord(dto);
		Page<AccountPlan> filter = service.filter(plan, pageable);
		return new PageImpl<>(filter.getContent(), pageable, filter.getTotalElements());
	}
	
	
	private AccountPlan convertRecord(AccountPlanDTO dto) {
		AccountPlan plan = new AccountPlan();
		plan.setCredit(dto.isCredit());
		plan.setName(dto.getName());
		return plan;
	}	

}
