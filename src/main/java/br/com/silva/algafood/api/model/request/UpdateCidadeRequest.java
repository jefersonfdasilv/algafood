package br.com.silva.algafood.api.model.request;

import lombok.Data;

@Data
public class UpdateCidadeRequest {
	private Long estadoId;
	private String nome;
}
