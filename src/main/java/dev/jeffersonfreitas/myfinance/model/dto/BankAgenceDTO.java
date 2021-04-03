package dev.jeffersonfreitas.myfinance.model.dto;

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
public class BankAgenceDTO{

	@NotBlank(message = "Informe o código da agencia")
	private String agence;
	
	@NotNull( message ="Informe um banco para a agencia")
	private Long bank;

}
