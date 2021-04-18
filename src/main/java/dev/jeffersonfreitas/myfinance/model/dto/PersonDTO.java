package dev.jeffersonfreitas.myfinance.model.dto;

import java.io.Serializable;

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
public class PersonDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	@NotBlank(message="Informe o nome")
	@Size(max=60, message="O nome deve ter no máximo 60 caracteres")
	private String name;
	
	private String phone;
	
	private String document;
	
	

}
