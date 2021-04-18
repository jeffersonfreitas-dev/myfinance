package dev.jeffersonfreitas.myfinance.api.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
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
import dev.jeffersonfreitas.myfinance.api.service.AccountSubPlanService;
import dev.jeffersonfreitas.myfinance.model.dto.AccountSubPlanDTO;
import dev.jeffersonfreitas.myfinance.model.entity.AccountPlan;
import dev.jeffersonfreitas.myfinance.model.entity.AccountSubPlan;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/accountsubplan")
@AllArgsConstructor
public class AccountSubPlanController {
	
	private AccountSubPlanService service;
	private AccountPlanService accountPlanService;
	private ModelMapper modelMapper;
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AccountSubPlan create(@RequestBody @Valid AccountSubPlanDTO dto) {
		AccountPlan account = accountPlanService.findById(dto.getAccountPlan());
		AccountSubPlan subAccount = modelMapper.map(dto, AccountSubPlan.class);
		subAccount.setAccountPlan(account);
		return subAccount = service.save(subAccount);
	}
	
	
	@GetMapping("{id}")
	public AccountSubPlan getAccountSubPlan(@PathVariable Long id) {
		return service.findById(id);
	}
	
	
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
	
	
	@PutMapping("{id}")
	public AccountSubPlan update(@PathVariable Long id, @Valid @RequestBody AccountSubPlanDTO dto) {
		AccountSubPlan subAccount = service.findById(id);
		AccountPlan account = accountPlanService.findById(dto.getAccountPlan());
		subAccount.setName(dto.getName());
		subAccount.setAccountPlan(account);
		return service.save(subAccount);
	}
	
	
	@GetMapping
	public Page<AccountSubPlan> filter (@RequestBody AccountSubPlanDTO dto, Pageable pageable){
		AccountSubPlan filter = new AccountSubPlan();
		
		if(dto.getAccountPlan() != null) {
			AccountPlan account = accountPlanService.findById(dto.getAccountPlan());
			filter.setAccountPlan(account);
		}
		filter.setName(dto.getName());
		Page<AccountSubPlan> result = service.filter(filter, pageable);
		return new PageImpl<>(result.getContent(), pageable, result.getTotalElements());
	}
	

}
