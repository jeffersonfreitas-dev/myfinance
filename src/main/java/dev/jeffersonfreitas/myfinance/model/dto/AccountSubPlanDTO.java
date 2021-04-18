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
public class AccountSubPlanDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "Informe um nome para a conta")
	private String name;
	
	@NotNull(message="Informe o plano de conta")
	private Long accountPlan;
}
