package br.com.silva.algafood.api.model.request;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CreateRestauranteRequest {

	@NotBlank
	private String nome;

	@NotNull
	private BigDecimal taxaFrete;

	@NotNull
	private Long cozinhaId;

	@NotNull
	private List<Long> formasPagamento = new ArrayList<>();

}
