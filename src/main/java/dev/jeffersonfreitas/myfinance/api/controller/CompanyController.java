package dev.jeffersonfreitas.myfinance.api.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
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

import dev.jeffersonfreitas.myfinance.api.service.CompanyService;
import dev.jeffersonfreitas.myfinance.model.dto.CompanyDTO;
import dev.jeffersonfreitas.myfinance.model.entity.Company;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/company")
@AllArgsConstructor
public class CompanyController {
	
	private final CompanyService service;
	private final ModelMapper modelMapper;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Company create(@RequestBody CompanyDTO dto) {
		Company company = modelMapper.map(dto, Company.class);
		company = service.save(company);
		return company;
	}
	
	
	@GetMapping("{id}")
	public Company getCompany(@PathVariable Long id) {
		return service.findById(id);
	}
	
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
	
	
	@GetMapping("all")
	public List<Company> getAllCompanies(){
		return service.findAllOrderByName();
	}
	
	
	@PutMapping("{id}")
	public Company update(@PathVariable Long id, @RequestBody CompanyDTO dto) {
		Company company = service.findById(id);
		company.setName(dto.getName());
		company.setDocument(dto.getDocument());
		return service.save(company);
	}
	
	
	@GetMapping
	public Page<Company> filter (@RequestBody CompanyDTO dto, Pageable pageable){
		Company filter = modelMapper.map(dto, Company.class);
		Page<Company> result = service.filter(filter, pageable);
		return new PageImpl<>(result.getContent(), pageable, result.getTotalElements());
	}

}
