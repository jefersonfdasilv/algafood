package br.com.silva.algafood.api.model.request;

import lombok.Data;

@Data
public class CreateCidadeRequest {
	private String uf;
	private String nome;
}
