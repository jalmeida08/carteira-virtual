package br.com.jsa.repository;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.jsa.model.Despesa;
import br.com.jsa.model.Pessoa;

public class DespesaRespository {

	@Inject
	private EntityManager manager;

	public Despesa getDespesa(Long idDespesa) {
		return manager.find(Despesa.class, idDespesa);
	}
	
	public Pessoa getPessoa(Long idPessoa) {
		return manager.find(Pessoa.class, idPessoa);
	}
	
	public void salvar(Despesa despesa) {
		manager.persist(despesa);
	}
	
	public Despesa detachDespesa(Despesa despesa) {
		System.out.println(despesa.getIdDespesa());
		manager.detach(despesa);
		System.out.println("Depois "+despesa.getIdDespesa());
		return despesa;
	}
	
	public void atualizar(Despesa despesa) {
		manager.merge(despesa);
	}
	
	public List<Despesa> listarTodasDespesas() {
		return manager.createQuery("select d from br.com.jsa.model.Despesa d", Despesa.class).getResultList();
	}
}
