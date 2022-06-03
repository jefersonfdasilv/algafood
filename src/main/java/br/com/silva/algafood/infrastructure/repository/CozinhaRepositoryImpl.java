package br.com.silva.algafood.infrastructure.repository;

import java.util.List;

import br.com.silva.algafood.domain.model.Cozinha;
import br.com.silva.algafood.domain.repository.CozinhaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
@AllArgsConstructor
public class CozinhaRepositoryImpl implements CozinhaRepository{

	@PersistenceContext
	protected EntityManager manager;
	@Override
	public List<Cozinha> listar() {
		return manager.createQuery("from Cozinha", Cozinha.class)
				.getResultList();
	}

	@Override
	public Cozinha buscar(Long id) {
		return manager.find(Cozinha.class, id);
	}

	@Override
	public Cozinha salvar(Cozinha cozinha) {
		return manager.merge(cozinha);
	}

	@Override
	public void remover(Cozinha cozinha) {
		Cozinha conzinha = buscar(cozinha.getId());
		manager.remove(conzinha);
		
	}

}
