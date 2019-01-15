package br.com.jsa.repository;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.jsa.model.FinanciamentoBancario;

public class FinanciamentoBancarioRepository {
	
	@Inject
	private EntityManager manager;

	public void salvar(FinanciamentoBancario financiamentoBancario) {
		manager.persist(financiamentoBancario);
	}
		
	public FinanciamentoBancario getFinanciamentoBancario(Long idFinanciamentoBancario) {
		return manager.find(FinanciamentoBancario.class, idFinanciamentoBancario);
	}
	
	public List<FinanciamentoBancario> listaFinanciamentosBancarios(){
		return manager.createQuery("select f from br.com.jsa.model.FinanciamentoBancario f", FinanciamentoBancario.class).getResultList();
	}
}
