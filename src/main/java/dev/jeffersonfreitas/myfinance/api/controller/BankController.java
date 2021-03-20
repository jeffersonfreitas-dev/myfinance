package dev.jeffersonfreitas.myfinance.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
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

import dev.jeffersonfreitas.myfinance.api.service.BankService;
import dev.jeffersonfreitas.myfinance.model.dto.BankDTO;
import dev.jeffersonfreitas.myfinance.model.entity.Bank;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/bank")
@AllArgsConstructor
public class BankController {
	
	private ModelMapper modelMapper;
	private BankService service;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public BankDTO create(@RequestBody @Valid BankDTO dto) {
		Bank bank = modelMapper.map(dto, Bank.class);
		bank = service.save(bank);
		return modelMapper.map(bank, BankDTO.class);
	}
	
	
	@GetMapping("{id}")
	public Bank getBook(@PathVariable Long id) {
		Bank bank = service.findById(id);
		return bank;
	}
	
	
	@GetMapping("/all")
	public List<Bank> getAllBanks(){
		return service.findAllOrderByName();
	}
	
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
	
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public BankDTO update (@PathVariable Long id, @RequestBody BankDTO dto) {
		Bank bank = service.update(id, dto);
		return modelMapper.map(bank, BankDTO.class);
	}
	
	

}
