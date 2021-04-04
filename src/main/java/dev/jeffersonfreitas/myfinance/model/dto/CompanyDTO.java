package dev.jeffersonfreitas.myfinance.model.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "Informe o nome da empresa")
	private String name;

	@NotBlank(message = "Informe o CPF/CNPJ da empresa")
	private String document;

}
