package br.com.silva.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import br.com.silva.algafood.domain.model.Cozinha;
import br.com.silva.algafood.domain.service.CadastroCozinhaService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController {

	private CadastroCozinhaService cadastroCozinha;

	@GetMapping
	public List<Cozinha> listar() {
		return cadastroCozinha.listar();
	}

	@GetMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId) {
		Optional<Cozinha> cozinhaOtional = cadastroCozinha.buscar(cozinhaId);

		return cozinhaOtional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Cozinha adicionar(@RequestBody Cozinha cozinha) {
		return cadastroCozinha.salvar(cozinha);
	}

	@PutMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha) {
		Optional<Cozinha> cozinhaOptional = cadastroCozinha.buscar(cozinhaId);

		return cozinhaOptional.map((c) -> {
			cozinha.setId(cozinhaId);
			cadastroCozinha.salvar(cozinha);
			return ResponseEntity.ok(cozinha);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{cozinhaId}")
	public ResponseEntity<Object> remover(@PathVariable Long cozinhaId) {

		try {
			cadastroCozinha.excluir(cozinhaId);
			return ResponseEntity.noContent().build();
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

}
