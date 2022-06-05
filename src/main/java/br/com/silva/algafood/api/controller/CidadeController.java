package br.com.silva.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.silva.algafood.api.model.request.CreateCidadeRequest;
import br.com.silva.algafood.api.model.request.UpdateCidadeRequest;
import br.com.silva.algafood.domain.exception.EntidadeEmUsoException;
import br.com.silva.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.silva.algafood.domain.model.Cidade;
import br.com.silva.algafood.domain.service.CadastroCidadeService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/cidades")
@AllArgsConstructor
public class CidadeController {

	private CadastroCidadeService cadastroCidade;

	@GetMapping
	public List<Cidade> listar() {
		return cadastroCidade.listar();
	}

	@GetMapping("/{cidadeId}")
	public ResponseEntity<Cidade> buscar(@PathVariable Long cidadeId) {
		Optional<Cidade> cidadeOptional = cadastroCidade.buscar(cidadeId);

		return cidadeOptional
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());

	}

	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody CreateCidadeRequest request) {
		try {
			var cidade = new Cidade();
			cidade.setNome(request.getNome());
			cidade = cadastroCidade.salvar(cidade, request.getUf());
			return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{cidadeId}")
	public ResponseEntity<?> atualizar(@PathVariable Long cidadeId, @RequestBody UpdateCidadeRequest request) {
		Optional<Cidade> cidadeOptional = cadastroCidade.buscar(cidadeId);

		try {
			return cidadeOptional.map((cidade)->{
				cidade.setNome(request.getNome());
				cadastroCidade.salvar(cidade, request.getEstadoId());
				return ResponseEntity.ok(cidade);
			}).orElse(ResponseEntity.notFound().build());
		}catch (EntidadeNaoEncontradaException e) {
			return	ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/{cidadeId}")
	public ResponseEntity<?> remover(@PathVariable Long cidadeId) {
		try {
			cadastroCidade.excluir(cidadeId);
			return ResponseEntity.noContent().build();

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();

		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}

}
