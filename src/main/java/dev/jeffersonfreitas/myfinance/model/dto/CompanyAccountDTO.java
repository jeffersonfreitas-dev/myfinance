package dev.jeffersonfreitas.myfinance.model.dto;

import java.io.Serializable;

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
public class CompanyAccountDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "Infome um código para identificação da conta")
	private String code;
	
	@NotBlank(message = "Informe o número da conta bancária")
	private String account;
	
	@NotNull(message = "Informe uma empresa")
	private Long company;
	
	@NotNull(message = "Informe uma agencia")
	private Long bankAgence;
	

}
