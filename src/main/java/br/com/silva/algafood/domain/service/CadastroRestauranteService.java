package br.com.silva.algafood.domain.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.silva.algafood.domain.exception.EntidadeEmUsoException;
import br.com.silva.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.silva.algafood.domain.model.Cozinha;
import br.com.silva.algafood.domain.model.Restaurante;
import br.com.silva.algafood.domain.repository.CozinhaRepository;
import br.com.silva.algafood.domain.repository.RestauranteRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CadastroRestauranteService {
	
	private RestauranteRepository restauranteRepository;
	private CozinhaRepository cozinhaRepository;
	
	public Restaurante salvar(Restaurante restaurante, Long cozinhaId) {
		
		Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);
		
		if( cozinha == null) {
			throw new EntidadeNaoEncontradaException("Cozinha informada não localizada.");
		}
		
		restaurante.setCozinha(cozinha);
		
		return restauranteRepository.salvar(restaurante);
	}
	
	public void excluir(Long restauranteId) {
		try {
			restauranteRepository.remover(restauranteId);
		}catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException("Restaurante não encontrado.");
		}catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException("Restaurante não pode ser removido pois esta em uso.");
		}
	}
}
