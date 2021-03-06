package br.com.silva.algafood.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.silva.algafood.domain.exception.EntidadeEmUsoException;
import br.com.silva.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.silva.algafood.domain.model.Estado;
import br.com.silva.algafood.domain.repository.EstadoRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CadastroEstadoService {

	private EstadoRepository estadoRepository;

	public Estado salvar(Estado estado) {
		return estadoRepository.save(estado);
	}

	public void excluir(Long estadoId) {
		try {
			estadoRepository.deleteById(estadoId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException("Estado não localizado.");
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException("Estado não pode ser excluído pois esta em uso.");
		}
	}

	public List<Estado> listar() {
		return estadoRepository.findAll();
	}

	public Optional<Estado> buscar(Long estadoId) {
		return estadoRepository.findById(estadoId);
	}
}
