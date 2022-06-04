package br.com.silva.algafood.domain.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.silva.algafood.domain.exception.EntidadeEmUsoException;
import br.com.silva.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.silva.algafood.domain.model.Cidade;
import br.com.silva.algafood.domain.model.Estado;
import br.com.silva.algafood.domain.repository.CidadeRepository;
import br.com.silva.algafood.domain.repository.EstadoRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CadastroCidadeService {
	
	private CidadeRepository cidadeRepository;
	private EstadoRepository estadoRepository;
	
	public Cidade salvar(Cidade cidade, Long estadoId) {
		
		Estado estado = estadoRepository.buscar(estadoId);
		
		if( estado == null) {
			throw new EntidadeNaoEncontradaException("Estado informado não localizado.");
		}
		
		cidade.setEstado(estado);
		
		return cidadeRepository.salvar(cidade);
	}
	
	public void excluir(Long cidadeId) {
		try {
			cidadeRepository.remover(cidadeId);
		}catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException("Cidade não encontrada.");
		}catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException("Cidade não pode ser removida pois esta em uso.");
		}
	}
}
