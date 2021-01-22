package dev.jeffersonfreitas.myfinance.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.jeffersonfreitas.myfinance.model.Banco;
import dev.jeffersonfreitas.myfinance.repository.BancoRepository;

@Service
public class BancoService {
	
	@Autowired
	private BancoRepository repository;
	
	public List<Banco> listarTodos(){
		return repository.listarTodosAtivosOrdenadoNome();
	}

	public Optional<Banco> findById(Long id) {
		return repository.findById(id);
	}

	public void delete(Banco banco) {
		repository.delete(banco);		
	}

	public Banco salvar(@Valid Banco banco) {
		return repository.save(banco);
	}

	
	public Banco update(Banco bb) {
		// TODO Auto-generated method stub
		return null;
	}

}
