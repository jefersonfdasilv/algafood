package br.com.silva.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.silva.algafood.domain.model.Cozinha;

public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

}
