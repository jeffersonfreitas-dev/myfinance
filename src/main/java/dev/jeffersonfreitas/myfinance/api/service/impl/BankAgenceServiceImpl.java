package dev.jeffersonfreitas.myfinance.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import dev.jeffersonfreitas.myfinance.api.service.BankAgenceService;
import dev.jeffersonfreitas.myfinance.api.service.exceptions.BusinessException;
import dev.jeffersonfreitas.myfinance.api.service.exceptions.RecordNotCanBeDeletedException;
import dev.jeffersonfreitas.myfinance.api.service.exceptions.RecordNotCanBeNullException;
import dev.jeffersonfreitas.myfinance.model.entity.BankAgence;
import dev.jeffersonfreitas.myfinance.model.repository.BankAgenceRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BankAgenceServiceImpl implements BankAgenceService{
	
	private BankAgenceRepository repository;

	@Override
	public BankAgence save(BankAgence agence) {
		if(agence == null) {
			throw new RecordNotCanBeNullException("Não pode salvar um registro nulo.");
		}
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
		try {
			repository.delete(agence);
		}catch(Exception e) {
			throw new RecordNotCanBeDeletedException("Ocorreu um erro e o registro não pode ser deletado.");
		}
	}

	
	@Override
	public Page<BankAgence> filter(BankAgence filter, Pageable pageable) {
		Example<BankAgence> result = Example.of(filter, 
				ExampleMatcher
					.matching()
					.withIgnoreCase()
					.withIgnoreNullValues()
					.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
				);
		return repository.findAll(result, pageable);
	}

	
	private void vefifyIfRecordAlreadyExists(BankAgence agence) {
		Optional<BankAgence> opt = repository.findByAgenceIgnoreCaseAndBankId(agence.getAgence(), agence.getBank().getId());
		if(opt.isPresent() && !opt.get().equals(agence)) {
			throw new BusinessException("Agencia já cadastrado para este banco");
		}
		
	}





}
