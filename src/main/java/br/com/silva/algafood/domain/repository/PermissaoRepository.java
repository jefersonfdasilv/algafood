package br.com.silva.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.silva.algafood.domain.model.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {

}
