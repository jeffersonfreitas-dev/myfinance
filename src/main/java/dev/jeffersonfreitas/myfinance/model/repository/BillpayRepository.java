package dev.jeffersonfreitas.myfinance.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.jeffersonfreitas.myfinance.model.entity.Billpay;
import dev.jeffersonfreitas.myfinance.model.entity.Company;

public interface BillpayRepository extends JpaRepository<Billpay, Long>{

	Optional<Billpay> findByInvoiceIgnoreCaseAndCompany(String invoice, Company company);
}
