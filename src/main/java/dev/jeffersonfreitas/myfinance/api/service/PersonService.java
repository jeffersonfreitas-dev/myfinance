package dev.jeffersonfreitas.myfinance.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.jeffersonfreitas.myfinance.model.entity.Person;

public interface PersonService {

	Person findById(Long id);

	Person save(Person person);

	void delete(Long id);

	Page<Person> filter(Person filter, Pageable pageable);
	

}
