package br.com.silva.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import br.com.silva.algafood.domain.model.Estado;
import br.com.silva.algafood.domain.repository.EstadoRepository;

@Repository
public class EstadoRepositoryImpl implements EstadoRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Estado> listar() {
		return manager.createQuery("from Estado", Estado.class)
				.getResultList();
	}
	
	@Override
	public Estado buscar(Long id) {
		return manager.find(Estado.class, id);
	}
	
	@Transactional
	@Override
	public Estado salvar(Estado estado) {
		return manager.merge(estado);
	}
	
	@Transactional
	@Override
	public void remover(Estado estado) {
		estado = buscar(estado.getId());
		if(estado != null)
			manager.remove(estado);
	}
	
	@Transactional
	@Override
	public void remover(Long estadoId) {
		var estado = buscar(estadoId);

		if (estado == null) {
			throw new EmptyResultDataAccessException(1);
		}
		manager.remove(estado);
	}

	@Override
	public Estado consultarPorUf(String uf) {
		
		return manager.createQuery("from Estado where uf = :uf", Estado.class)
				.setParameter("uf", uf.toUpperCase()).getSingleResult();
	}
}