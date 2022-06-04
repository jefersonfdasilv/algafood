package br.com.silva.algafood.infrastructure.repository;

import java.util.List;

import javax.management.RuntimeErrorException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import br.com.silva.algafood.domain.model.Cozinha;
import br.com.silva.algafood.domain.repository.CozinhaRepository;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Cozinha> listar() {
		return manager.createQuery("from Cozinha", Cozinha.class).getResultList();
	}

	@Override
	public Cozinha buscar(Long id) {
		return manager.find(Cozinha.class, id);
	}

	@Transactional
	@Override
	public Cozinha salvar(Cozinha cozinha) {
		return manager.merge(cozinha);
	}

	@Transactional
	@Override
	public void remover(Cozinha cozinha) {
		cozinha = buscar(cozinha.getId());
		manager.remove(cozinha);
	}

	@Transactional
	public void remover(Long cozinhaId) {
		var cozinha = buscar(cozinhaId);

		if (cozinha == null) {
			throw new EmptyResultDataAccessException(1);
		}
		manager.remove(cozinha);
	}

}