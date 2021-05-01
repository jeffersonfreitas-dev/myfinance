package dev.jeffersonfreitas.myfinance.model.entity;

import java.time.LocalDate;

import javax.persistence.Column;
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
@Table(name = "billpay")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Billpay {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
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
	
	@Builder.Default
	private Integer portion = 1;
	
	@Builder.Default
	private Integer installment = 1;
	
	@ManyToOne
	@JoinColumn(name = "id_provider")
	@NotNull(message = "Informe o fornecedor")
	private Person provider;
	
	@ManyToOne
	@JoinColumn(name = "id_account_subplan")
	@NotNull(message = "Informe o plano de conta")
	private AccountSubPlan accountSubplan;
	
	@ManyToOne
	@JoinColumn(name = "id_company")
	@NotNull(message = "Informe a empresa")
	private Company company;
}
