package br.com.jsa.repository;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.jsa.model.InstituicaoFinanceira;

public class InstituicaoFinanceiraRepository {
	
	@Inject
	private EntityManager manager;

	public void salvar(InstituicaoFinanceira instituicaoFinanceira) {
		manager.persist(instituicaoFinanceira);
	}
	
	public InstituicaoFinanceira getInstituicaoFinanceira(Long idInstituicaoFinanceira) {
		return manager.find(InstituicaoFinanceira.class, idInstituicaoFinanceira);
	}
	
	public List<InstituicaoFinanceira> instituicoesFinanceiras(){
		return manager.createQuery("select i from br.com.jsa.model.InstituicaoFinanceira i", InstituicaoFinanceira.class)
				.getResultList();
	}
}
