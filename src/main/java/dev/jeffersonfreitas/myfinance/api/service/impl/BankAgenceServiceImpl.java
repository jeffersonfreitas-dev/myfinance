package dev.jeffersonfreitas.myfinance.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import dev.jeffersonfreitas.myfinance.api.service.BankAgenceService;
import dev.jeffersonfreitas.myfinance.api.service.exceptions.BusinessException;
import dev.jeffersonfreitas.myfinance.model.entity.BankAgence;
import dev.jeffersonfreitas.myfinance.model.repository.BankAgenceRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BankAgenceServiceImpl implements BankAgenceService{
	
	private BankAgenceRepository repository;

	@Override
	public BankAgence save(BankAgence agence) {
		vefifyIfRecordAlreadyExists(agence);
		return repository.save(agence);
	}
	
	
	@Override
	public BankAgence findById(Long id) {
		return repository.findById(id).orElseThrow( 
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro não encontrado. ID:" + id));
	}
	
	
	
	@Override
	public List<BankAgence> findAllOrderByAgence() {
		return repository.findAllByOrderByAgenceAsc();
	}


	@Override
	public void delete(Long id) {
		BankAgence agence = findById(id);
		repository.delete(agence);
	}
	

	@SuppressWarnings("unlikely-arg-type")
	private void vefifyIfRecordAlreadyExists(BankAgence agence) {
		Optional<BankAgence> opt = repository.findByAgenceIgnoreCaseAndBankId(agence.getAgence(), agence.getBank().getId());
		if(opt.isPresent() && !opt.equals(agence)) {
			throw new BusinessException("Agencia já cadastrado para este banco");
		}
		
	}


}
