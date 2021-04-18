package dev.jeffersonfreitas.myfinance.api.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.jeffersonfreitas.myfinance.api.service.PersonService;
import dev.jeffersonfreitas.myfinance.model.dto.PersonDTO;
import dev.jeffersonfreitas.myfinance.model.entity.Person;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/person")
@AllArgsConstructor
public class PersonController {
	
	private final PersonService service;
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Person create(@RequestBody @Valid PersonDTO dto) {
		Person person = convertRecord(dto);
		person = service.save(person);
		return person;
	}
	
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
	
	
	@PutMapping("{id}")
	public Person update(@PathVariable Long id, @RequestBody PersonDTO dto) {
		Person person = service.findById(id);
		person.setName(dto.getName());
		person.setPhone(dto.getPhone());
		person.setDocument(dto.getDocument());
		return service.save(person);
	}


	@GetMapping("{id}")
	public Person getAccount(@PathVariable Long id) {
		return service.findById(id);
	}
	
	
	@GetMapping
	public Page<Person> filter(@RequestBody PersonDTO dto, Pageable pageable){
		Person person = convertRecord(dto);
		Page<Person> filter = service.filter(person, pageable);
		return new PageImpl<>(filter.getContent(), pageable, filter.getTotalElements());
	}
	
	
	private Person convertRecord(PersonDTO dto) {
		Person person = new Person();
		person.setName(dto.getName());
		person.setPhone(dto.getPhone());
		person.setDocument(dto.getDocument());
		return person;
	}	

}
