package br.com.silva.algafood.domain.service;

import java.util.List;
import java.util.Optional;

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

		Optional<Estado> estado = estadoRepository.findById(estadoId);
		return estado.map((e) -> {
			return persiste(cidade, e);
		}).orElseThrow(
				() -> new EntidadeNaoEncontradaException("Estado com ID (" + estadoId + ") informado não localizado."));
	}

	public Cidade salvar(Cidade cidade, String uf) {
		Optional<Estado> estado = estadoRepository.findByUf(uf);
		return estado.map((e) -> {
			return persiste(cidade, e);
		}).orElseThrow(() -> new EntidadeNaoEncontradaException("Estado com UF(" + uf + ") informado não localizado."));
	}

	private Cidade persiste(Cidade cidade, Estado estado) {
		cidade.setEstado(estado);
		return cidadeRepository.save(cidade);
	}

	public void excluir(Long cidadeId) {
		try {
			cidadeRepository.deleteById(cidadeId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException("Cidade não encontrada.");
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException("Cidade não pode ser removida pois esta em uso.");
		}
	}

	public List<Cidade> listar() {
		return cidadeRepository.findAll();
	}

	public Optional<Cidade> buscar(Long cidadeId) {
		return cidadeRepository.findById(cidadeId);
	}

}
