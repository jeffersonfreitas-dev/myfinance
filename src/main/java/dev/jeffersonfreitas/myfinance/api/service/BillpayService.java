package dev.jeffersonfreitas.myfinance.api.service;

import dev.jeffersonfreitas.myfinance.model.entity.Billpay;

public interface BillpayService {

	Billpay findById(Long id);

	Billpay save(Billpay bill);

	void delete(Long id);

}
