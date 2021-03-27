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
@Table(name = "bank_agence")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankAgence implements Serializable{
	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Informe o código da agencia")
	private String agence;
	
	@NotNull( message ="Informe um banco para a agencia")
	@ManyToOne
	@JoinColumn(name="id_bank")
	private Bank bank;

}
