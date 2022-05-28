package br.com.silva.algafood.infrastructure.repository;

import java.util.List;

import br.com.silva.algafood.domain.model.Cozinha;
import br.com.silva.algafood.domain.repository.CozinhaRepository;


public class CozinhaRepositoryImpl extends BaseRepository implements CozinhaRepository{

	
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
