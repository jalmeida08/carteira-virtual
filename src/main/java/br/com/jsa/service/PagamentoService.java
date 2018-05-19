package br.com.jsa.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.com.jsa.model.Pagamento;
import br.com.jsa.repository.PagamentoRespository;

@Stateless
public class PagamentoService {
	@Inject
	private PagamentoRespository pagamentoRespository;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void salvar(Pagamento pagamento) {
		pagamentoRespository.salvar(pagamento);
	}
	
	public Pagamento getPagamento(Long idPagamento) {
		return pagamentoRespository.getPagamento(idPagamento);
	}
	
	public List<Pagamento>buscarPagamentos() {
		return pagamentoRespository.buscarTodosPagamentos();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualizar(Pagamento pagamento) {
		pagamentoRespository.atualizar(pagamento);
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void statusPagamento(Pagamento pagamento) {
		pagamentoRespository.statusPagamento(pagamento);
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remover (Long idPagamento) {
		pagamentoRespository.remover(idPagamento);
	}
}
