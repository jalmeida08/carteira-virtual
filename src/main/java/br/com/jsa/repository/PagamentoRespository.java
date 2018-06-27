package br.com.jsa.repository;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.jsa.model.Pagamento;
import br.com.jsa.model.Pessoa;
import br.com.jsa.model.StatusPagamento;

public class PagamentoRespository {

	@Inject
	private EntityManager manager;

	public void salvar(Pagamento pagamento) {
		if (pagamento.getPessoa() != null) {
			Pessoa pessoa = getPessoa(pagamento.getPessoa().getIdPessoa());
			pagamento.setPessoa(pessoa);
		}
		manager.persist(pagamento);
	}

	public Pessoa getPessoa(Long idPessoa) {
		return manager.find(Pessoa.class, idPessoa);
	}

	public Pagamento getPagamento(Long idPagamento) {
		return manager.find(Pagamento.class, idPagamento);
	}

	public List<Pagamento> buscarTodosPagamentos() {
		return manager.createQuery("select p from br.com.jsa.model.Pagamento p", Pagamento.class).getResultList();
	}

	public void remover(Long idPagamento) {
		Pagamento pagamento = getPagamento(idPagamento);
		manager.remove(pagamento);
	}

	public void atualizar(Pagamento pagamento) {
		Pagamento pag = getPagamento(pagamento.getIdPagamento());
		pag.setDataPagamento(pagamento.getDataPagamento());
		pag.setStatusPagamento(pagamento.getStatusPagamento());
		pag.setValor(pagamento.getValor());
		pag.setFixo(pagamento.isFixo());
		manager.merge(pag);
	}

	public void statusPagamento(Pagamento pagamento) {
		Pagamento pag = getPagamento(pagamento.getIdPagamento());
		pag.setStatusPagamento(pagamento.getStatusPagamento());
		manager.merge(pag);
	}
	
	public List<Pagamento> checarPagamentoDoDia(Date dataAtual) {
		return 
				manager.createQuery("select p from br.com.jsa.model.Pagamento p where p.fixo = true and p.dataPagamento = :dataPagamento",
						Pagamento.class)
				.setParameter("dataPagamento", dataAtual)
				.getResultList();
	}
	
	public void fecharPagamento(Long idPagamento) {
		Pagamento pagamento = this.getPagamento(idPagamento);
		pagamento.setStatusPagamento(StatusPagamento.RECEBIDO);
		manager.merge(pagamento);
	}
}
