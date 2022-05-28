package br.com.silva.algafood.infrastructure.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

@Component
public class BaseRepository {
	
	@PersistenceContext
	protected EntityManager manager;
	
}
