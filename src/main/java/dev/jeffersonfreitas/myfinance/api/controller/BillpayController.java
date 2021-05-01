package dev.jeffersonfreitas.myfinance.api.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.jeffersonfreitas.myfinance.api.service.AccountSubPlanService;
import dev.jeffersonfreitas.myfinance.api.service.BillpayService;
import dev.jeffersonfreitas.myfinance.api.service.CompanyService;
import dev.jeffersonfreitas.myfinance.api.service.PersonService;
import dev.jeffersonfreitas.myfinance.model.dto.BillpayDTO;
import dev.jeffersonfreitas.myfinance.model.entity.Billpay;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/billpay")
@AllArgsConstructor
public class BillpayController {
	
	private final BillpayService service;
	private final CompanyService companyService;
	private final PersonService providerService;
	private final AccountSubPlanService accountService;
	
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public Billpay create(@RequestBody @Valid BillpayDTO dto) {
		Billpay bill = convertDtoToEntity(dto);
		bill = service.save(bill);
		return bill;
	}
	

	@GetMapping("{id}")
	public Billpay getBillpay(@PathVariable Long id) {
		return service.findById(id);
	}
	
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
	
	
	private Billpay convertDtoToEntity(BillpayDTO dto) {
		Billpay bill = new Billpay();
		bill.setDueDate(dto.getDueDate());
		bill.setHistoric(dto.getHistoric());
		bill.setEmissionDate(dto.getEmissionDate());
		bill.setInstallment(dto.getInstallment());
		bill.setInvoice(dto.getInvoice());
		bill.setPortion(dto.getPortion());
		bill.setValue(dto.getValue());
		bill.setAccountSubplan(accountService.findById(dto.getAccountSubplan()));
		bill.setCompany(companyService.findById(dto.getCompany()));
		bill.setProvider(providerService.findById(dto.getProvider()));
		return bill;
	}
	

}
