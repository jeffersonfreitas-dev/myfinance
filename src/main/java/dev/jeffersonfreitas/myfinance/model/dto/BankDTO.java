package dev.jeffersonfreitas.myfinance.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankDTO {
	
	@NotBlank(message="Informe o código do banco")
	@Size(min=3, max=3, message="O código do banco tem que ter três caracteres")
	private String code;
	
	@NotBlank(message="Informe o nome do banco")
	@Size(max=60, message="O nome deve ter no máximo 60 caracteres")
	private String name;

}
