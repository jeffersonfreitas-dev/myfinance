package dev.jeffersonfreitas.myfinance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.jeffersonfreitas.myfinance.model.Banco;

public interface BancoRepository extends JpaRepository<Banco, Long>{

	@Query("select b from Banco b where b.ativo = 1 order by b.nome")
	List<Banco> listarTodosAtivosOrdenadoNome();

}
