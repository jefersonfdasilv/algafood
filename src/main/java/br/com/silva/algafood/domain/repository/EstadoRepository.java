package br.com.silva.algafood.domain.repository;

import br.com.silva.algafood.domain.model.Estado;

public interface EstadoRepository extends CRUDRepository<Estado> {
	
	public Estado consultarPorUf(String uf);

}
