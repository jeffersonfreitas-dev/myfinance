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
@Table(name = "company_account")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyAccount implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Infome um código para identificação da conta")
	private String code;
	
	@NotBlank(message = "Informe o número da conta bancária")
	private String account;
	
	@NotNull(message = "Informe uma empresa")
	@ManyToOne
	@JoinColumn(name = "id_company")
	private Company company;
	
	@NotNull(message = "Informe uma agencia")
	@ManyToOne
	@JoinColumn(name = "id_bank_agence")
	private BankAgence bankAgence;
	

}
