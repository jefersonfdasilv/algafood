package br.com.silva.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.silva.algafood.domain.model.Restaurante;

public interface RestauranteRepository  extends RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante>, JpaRepository<Restaurante, Long> {

}
