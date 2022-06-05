package br.com.silva.algafood.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.silva.algafood.domain.exception.EntidadeEmUsoException;
import br.com.silva.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.silva.algafood.domain.model.Cozinha;
import br.com.silva.algafood.domain.repository.CozinhaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CadastroCozinhaService {
	
	private CozinhaRepository cozinhaRepository;
	
	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}
	
	public void excluir(Long cozinhaId) {
		try {
			cozinhaRepository.deleteById(cozinhaId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException("Cozinha não localizada.");
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException("Cozinha não pode ser excluída pois esta em uso.");
		}
	}

	public List<Cozinha> listar() {
		return cozinhaRepository.findAll();
	}

	public Optional<Cozinha> buscar(Long cozinhaId) {
		return cozinhaRepository.findById(cozinhaId);
	}
}
