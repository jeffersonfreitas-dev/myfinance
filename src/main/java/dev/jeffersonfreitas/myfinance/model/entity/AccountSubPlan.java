package dev.jeffersonfreitas.myfinance.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "account_subplan")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountSubPlan implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Informe um nome para a conta")
	private String name;
	
	@NotNull(message="Informe o plano de conta")
	@ManyToOne
	@JoinColumn(name = "id_accountplan")
	private AccountPlan accountPlan;
}
