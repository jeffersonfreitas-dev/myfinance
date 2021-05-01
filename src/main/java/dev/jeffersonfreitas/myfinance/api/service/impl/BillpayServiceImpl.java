package dev.jeffersonfreitas.myfinance.api.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import dev.jeffersonfreitas.myfinance.api.service.BillpayService;
import dev.jeffersonfreitas.myfinance.api.service.exceptions.BusinessException;
import dev.jeffersonfreitas.myfinance.api.service.exceptions.RecordNotCanBeDeletedException;
import dev.jeffersonfreitas.myfinance.api.service.exceptions.RecordNotCanBeNullException;
import dev.jeffersonfreitas.myfinance.model.entity.Billpay;
import dev.jeffersonfreitas.myfinance.model.repository.BillpayRepository;

@Service
public class BillpayServiceImpl implements BillpayService{

	@Autowired
	private BillpayRepository repository;
	
	@Override
	public Billpay findById(Long id) {
		return repository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro não encontrado. ID:" + id));
	}

	
	@Override
	public Billpay save(Billpay bill) {
		if(bill == null) {
			throw new RecordNotCanBeNullException("Não pode salvar um registro nulo.");
		}
		verifyIfRecordAlreadyRecorded(bill);
		return repository.save(bill);
	}
	
	
	@Override
	public void delete(Long id) {
		Billpay bill = findById(id);
		try {
			repository.delete(bill);
		}catch(Exception e) {
			throw new RecordNotCanBeDeletedException("Ocorreu um erro e o registro não pode ser deletado.");
		}
	}

	
	private void verifyIfRecordAlreadyRecorded(Billpay bill) {
		Optional<Billpay> opt = repository.findByInvoiceIgnoreCaseAndCompany(bill.getInvoice(), bill.getCompany());
		
		if(opt.isPresent() && !opt.get().equals(bill)) {
			throw new BusinessException("Já existe uma conta com esta nota e fornecedor cadastrado.");
		}
	}

}
