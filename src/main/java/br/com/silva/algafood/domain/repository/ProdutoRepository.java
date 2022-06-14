package br.com.silva.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.silva.algafood.domain.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
