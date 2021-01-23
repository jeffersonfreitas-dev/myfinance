package dev.jeffersonfreitas.myfinance.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.jeffersonfreitas.myfinance.model.Banco;
import dev.jeffersonfreitas.myfinance.repository.BancoRepository;
import dev.jeffersonfreitas.myfinance.service.exceptions.ObjectNullException;

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
		if(banco == null || banco.getId() == null) {
			throw new ObjectNullException("Objeto nulo!");
		}
		repository.delete(banco);		
	}

	public Banco salvar(@Valid Banco banco) {
		return repository.save(banco);
	}

	
	public Banco update(Banco banco) {
		if(banco == null || banco.getId() == null) {
			throw new ObjectNullException("Objeto nulo!");
		}
		return repository.save(banco);
	}

}
