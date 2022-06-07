package br.com.silva.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.silva.algafood.domain.model.Restaurante;

public interface RestauranteRepository  extends RestauranteRepositoryQueries, JpaRepository<Restaurante, Long> {

}
