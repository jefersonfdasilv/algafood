package br.com.silva.algafood.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.silva.algafood.domain.exception.EntidadeEmUsoException;
import br.com.silva.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.silva.algafood.domain.model.Cozinha;
import br.com.silva.algafood.domain.model.FormaPagamento;
import br.com.silva.algafood.domain.model.Restaurante;
import br.com.silva.algafood.domain.repository.CozinhaRepository;
import br.com.silva.algafood.domain.repository.FormaPagamentoRepository;
import br.com.silva.algafood.domain.repository.RestauranteRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CadastroRestauranteService {

	private RestauranteRepository restauranteRepository;
	private CozinhaRepository cozinhaRepository;
	private FormaPagamentoRepository formaPagamentoRepository;

	public Restaurante salvar(Restaurante restaurante, Long cozinhaId, List<Long> formasPagamentoId) {

		Optional<Cozinha> cozinhaOtional = cozinhaRepository.findById(cozinhaId);
		List<FormaPagamento> formasPagamentos = formaPagamentoRepository.findAll();

		var formasPagamentoRestaurante = formasPagamentos.stream().filter((fp) -> {
			return formasPagamentoId.contains(fp.getId());
		}).collect(Collectors.toList());


		return cozinhaOtional.map((cozinha) -> {
			restaurante.setCozinha(cozinha);
			restaurante.setFormasPagamento(formasPagamentoRestaurante);
			return restauranteRepository.save(restaurante);
		}).orElseThrow(() -> new EntidadeNaoEncontradaException("Cozinha informada não localizada."));
	}

	public Restaurante salvar(Restaurante restaurante, Long cozinhaId) {

		Optional<Cozinha> cozinhaOtional = cozinhaRepository.findById(cozinhaId);

		return cozinhaOtional.map((cozinha) -> {
			restaurante.setCozinha(cozinha);
			return restauranteRepository.save(restaurante);
		}).orElseThrow(() -> new EntidadeNaoEncontradaException("Cozinha informada não localizada."));
	}

	public void excluir(Long restauranteId) {
		try {
			restauranteRepository.deleteById(restauranteId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException("Restaurante não encontrado.");
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException("Restaurante não pode ser removido pois esta em uso.");
		}
	}

	public List<Restaurante> listar() {
		return restauranteRepository.findAll();
	}

	public Optional<Restaurante> buscar(Long restauranteId) {
		return restauranteRepository.findById(restauranteId);
	}
}
