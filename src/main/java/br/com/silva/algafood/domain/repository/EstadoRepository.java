package br.com.silva.algafood.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.silva.algafood.domain.model.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long> {

	public Optional<Estado> findByUf(String uf);

}
