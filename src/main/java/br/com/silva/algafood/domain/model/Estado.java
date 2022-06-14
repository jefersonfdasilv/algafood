package br.com.silva.algafood.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "estados")
public class Estado {

	@EqualsAndHashCode.Include
	@Id
	private Long id;

	@Column(nullable = false, length = 200)
	private String nome;

	@Column(nullable = false, length = 2, unique = true)
	private String uf;

}
