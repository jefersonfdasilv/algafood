package br.com.silva.algafood.domain.repository;

import java.util.List;

public interface CRUDRepository<T> {
	List<T> listar();
	T buscar(Long id);
	T salvar(T model);
	void remover(T model);
}
