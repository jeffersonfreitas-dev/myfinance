package dev.jeffersonfreitas.myfinance.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.jeffersonfreitas.myfinance.model.entity.AccountPlan;

public interface AccountPlanRepository extends JpaRepository<AccountPlan, Long>{

	Optional<AccountPlan> findByNameIgnoreCase(String name);

}
