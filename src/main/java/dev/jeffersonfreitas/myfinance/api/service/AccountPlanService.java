package dev.jeffersonfreitas.myfinance.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.jeffersonfreitas.myfinance.model.entity.AccountPlan;

public interface AccountPlanService {

	
	AccountPlan findById(Long id);

	AccountPlan save(AccountPlan accountPlan);

	void delete(Long id);

	Page<AccountPlan> filter(AccountPlan plan, Pageable pageable);

}
