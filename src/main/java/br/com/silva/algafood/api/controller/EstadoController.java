package br.com.silva.algafood.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.silva.algafood.domain.exception.EntidadeEmUsoException;
import br.com.silva.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.silva.algafood.domain.model.Estado;
import br.com.silva.algafood.domain.service.CadastroEstadoService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/estados")
@AllArgsConstructor
public class EstadoController {

	private CadastroEstadoService cadastroEstado;
	@GetMapping
	public List<Estado> listar() {
		return cadastroEstado.listar();
	}

	@GetMapping("/{estadoId}")
	public ResponseEntity<Estado> buscar(@PathVariable Long estadoId) {
		var estado = cadastroEstado.buscar(estadoId);

		return estado
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Estado adicionar(@RequestBody Estado estado) {
		return cadastroEstado.salvar(estado);
	}

	@PutMapping("/{estadoId}")
	public ResponseEntity<Estado> atualizar(@PathVariable Long estadoId, @RequestBody Estado estado) {
		var estadoAtual = cadastroEstado.buscar(estadoId);

		if(estadoAtual.isEmpty()) {
			return ResponseEntity.notFound().build();

		}

		estado.setId(estadoId);
		estado = cadastroEstado.salvar(estado);

		return ResponseEntity.ok(estado);
	}

	@DeleteMapping("/{estadoId}")
	public ResponseEntity<Object> remover(@PathVariable Long estadoId) {

		try {
			cadastroEstado.excluir(estadoId);
			return ResponseEntity.noContent().build();
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
