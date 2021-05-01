package dev.jeffersonfreitas.myfinance.model.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillpayDTO {
	
	@NotBlank(message = "Informe a nota fiscal")
	private String invoice;
	
	@NotBlank(message = "Informe um histórico para a conta")
	private String historic;
	
	@NotNull(message = "Informe a data de emissão")
	@Column(name = "emission_date")
	private LocalDate emissionDate;
	
	@NotNull(message = "Informe o vencimento")
	@Column(name = "due_date")
	private LocalDate dueDate;
	
	@NotNull(message = "Informe o valor")
	private Double value;
	
	private Integer portion;
	
	private Integer installment;
	
	@NotNull(message = "Informe o fornecedor")
	private Long provider;
	
	@NotNull(message = "Informe o plano de conta")
	private Long accountSubplan;
	
	@NotNull(message = "Informe a empresa")
	private Long company;	
}
