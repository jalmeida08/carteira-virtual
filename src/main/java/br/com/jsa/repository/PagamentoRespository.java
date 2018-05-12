package br.com.jsa.repository;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.jsa.model.Pagamento;

public class PagamentoRespository {

	@Inject
	private EntityManager manager;
	
	public void salvar(Pagamento pagamento) {
		manager.persist(pagamento);
	}
	
	public Pagamento getPagamento (Long idPagamento) {
		return manager.find(Pagamento.class, idPagamento);
	}
	
	public List<Pagamento> buscarTodosPagamentos(){
		return manager.createQuery("select p from br.com.jsa.model.Pagamento p", Pagamento.class).getResultList();
	}
	
	public void remover(Long idPagamento) {
		Pagamento pagamento = getPagamento(idPagamento);
		manager.remove(pagamento);
	}
	
	public void statusPagamento(Pagamento pagamento) {
		
	}
}
