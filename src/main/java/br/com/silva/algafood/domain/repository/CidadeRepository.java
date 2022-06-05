package br.com.silva.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.silva.algafood.domain.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade,Long> {

}
