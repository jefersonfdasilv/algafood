package br.com.silva.algafood.api.model.request;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CreateRestauranteRequest {
	
	private String nome;
	
	private BigDecimal taxaFrete;
	
	private Long cozinhaId;
}
