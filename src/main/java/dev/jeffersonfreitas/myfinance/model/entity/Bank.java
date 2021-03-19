package dev.jeffersonfreitas.myfinance.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bank implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="Informe o código do banco")
	@Size(min=3, max=3, message="O código do banco tem que ter três caracteres")
	private String code;
	
	@NotBlank(message="Informe o nome do banco")
	@Size(max=60, message="O nome deve ter no máximo 60 caracteres")
	private String name;
	
	

}
