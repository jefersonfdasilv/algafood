package br.com.silva.algafood.domain.repository;

import java.util.List;

public interface CRUDRepository<T> {
	List<T> findAll();
	T findById(Long id);
	T save(T model);
	void delete(T model);
	void deleteById(Long id);
}
