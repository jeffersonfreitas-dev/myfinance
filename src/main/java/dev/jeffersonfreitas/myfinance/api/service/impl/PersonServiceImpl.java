package dev.jeffersonfreitas.myfinance.api.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import dev.jeffersonfreitas.myfinance.api.service.PersonService;
import dev.jeffersonfreitas.myfinance.api.service.exceptions.BusinessException;
import dev.jeffersonfreitas.myfinance.api.service.exceptions.RecordNotCanBeDeletedException;
import dev.jeffersonfreitas.myfinance.api.service.exceptions.RecordNotCanBeNullException;
import dev.jeffersonfreitas.myfinance.model.entity.Person;
import dev.jeffersonfreitas.myfinance.model.repository.PersonRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService{
	
	private PersonRepository repository;

	
	@Override
	public Person save(Person person) {
		if(person == null) {
			throw new RecordNotCanBeNullException("Não pode salvar um registro nulo.");
		}
		vefifyIfRecordAlreadyExists(person);
		return repository.save(person);
	}
	
	
	@Override
	public Person findById(Long id) {
		return repository.findById(id).orElseThrow( 
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro não encontrado. ID:" + id));
	}
	
	
	
	@Override
	public void delete(Long id) {
		Person person = findById(id);
		try {
			repository.delete(person);
		}catch(Exception e) {
			throw new RecordNotCanBeDeletedException("Ocorreu um erro e o registro não pode ser deletado.");
		}
	}

	
	
	@Override
	public Page<Person> filter(Person filter, Pageable pageable) {
		Example<Person> result = Example.of(filter, 
				ExampleMatcher
					.matching()
					.withIgnoreCase()
					.withIgnoreNullValues()
					.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
				);
		return repository.findAll(result, pageable);
	}

	
	private void vefifyIfRecordAlreadyExists(Person person) {
		Optional<Person> opt = repository.findByNameIgnoreCase(person.getName());
		if(opt.isPresent() && !opt.get().equals(person)) {
			throw new BusinessException("Já existe uma pessoa cadastrado com este nome.");
		}
		
	}

}
