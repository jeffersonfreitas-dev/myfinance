package dev.jeffersonfreitas.myfinance.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.jeffersonfreitas.myfinance.model.entity.AccountSubPlan;

public interface AccountSubPlanService {

	AccountSubPlan save(AccountSubPlan account);

	AccountSubPlan findById(Long id);

	void delete(Long id);

	Page<AccountSubPlan> filter(AccountSubPlan filter, Pageable pageable);

}
