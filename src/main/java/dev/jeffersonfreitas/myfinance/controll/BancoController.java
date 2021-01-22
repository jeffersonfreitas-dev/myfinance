package dev.jeffersonfreitas.myfinance.controll;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import dev.jeffersonfreitas.myfinance.model.Banco;
import dev.jeffersonfreitas.myfinance.service.BancoService;

@RestController
@RequestMapping("api/bancos")
public class BancoController {
	
	@Autowired
	private BancoService service;
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Banco salvar(@RequestBody @Valid Banco banco) {
		return service.salvar(banco);
	}
	
	
	@PutMapping("{id}")
	public Banco atualizar(@PathVariable Long id, @RequestBody Banco banco) {
		Banco bb = buscarPorId(id);
		bb.setAtivo(banco.isAtivo());
		bb.setCodigo(banco.getCodigo());
		bb.setNome(banco.getNome());
		bb = service.update(bb);
		return bb;
	}
	
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Banco> listarTodos() {
		return service.listarTodos();
	}
	
	
	@GetMapping("{id}")
	public Banco buscarPorId(@PathVariable Long id) {
		return service.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
	}
	
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		Banco banco = buscarPorId(id);
		service.delete(banco);
	}

}
