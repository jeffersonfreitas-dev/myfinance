package dev.jeffersonfreitas.myfinance.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.jeffersonfreitas.myfinance.model.entity.AccountPlan;
import dev.jeffersonfreitas.myfinance.model.entity.AccountSubPlan;

public interface AccountSubPlanRepository extends JpaRepository<AccountSubPlan, Long>{

	Optional<AccountSubPlan> findByNameIgnoreCaseAndAccountPlan(String name, AccountPlan accountPlan);

}
