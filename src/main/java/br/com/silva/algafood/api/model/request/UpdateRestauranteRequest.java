package br.com.silva.algafood.api.model.request;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UpdateRestauranteRequest {
	
	@NotBlank
	private String nome;
	@Min(0)
	private BigDecimal taxaFrete;
	@Min(1)
	private Long cozinhaId;
}
