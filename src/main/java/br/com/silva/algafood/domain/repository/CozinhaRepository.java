package br.com.silva.algafood.domain.repository;

import br.com.silva.algafood.domain.model.Cozinha;

public interface CozinhaRepository extends CRUDRepository<Cozinha> {
	public void remover(Long cozinhaId);
}
