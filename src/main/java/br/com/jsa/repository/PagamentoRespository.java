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
			Pessoa pessoa = manager.find(Pessoa.class, pagamento.getPessoa().getIdPessoa());
			pagamento.setPessoa(pessoa);
		}
		manager.persist(pagamento);
	}

	public Pagamento getPagamento(Long idPagamento, Long idUsuario) {
		return manager.createQuery(
				"select p from br.com.jsa.model.Pagamento p where p.usuario.idUsuario = :idUsuario and idPagamento = :idPagamento", Pagamento.class)
				.setParameter("idUsuario", idUsuario)
				.setParameter("idPagamento", idPagamento)
				.getSingleResult();
	}

	public List<Pagamento> buscarTodosPagamentosUsuario(Long idUsuario) {
		return manager.createQuery(
				"select p from br.com.jsa.model.Pagamento p where p.usuario.idUsuario = :idUsuario", Pagamento.class)
				.setParameter("idUsuario", idUsuario)
				.getResultList();
	}

	public void remover(Long idPagamento, Long idUsuario) {
		Pagamento pagamento = manager.createQuery(
				"select p from br.com.jsa.model.Pagamento p where p.usuario.idUsuario = :idUsuario and idPagamento = :idPagamento", Pagamento.class)
				.setParameter("idUsuario", idUsuario)
				.setParameter("idPagamento", idPagamento)
				.getSingleResult();
		manager.remove(pagamento);
	}

	public void atualizar(Pagamento pagamento) {
		Pagamento pag = getPagamento(pagamento.getIdPagamento(), pagamento.getUsuario().getIdUsuario());
		pag.setDataPagamento(pagamento.getDataPagamento());
		pag.setStatusPagamento(pagamento.getStatusPagamento());
		pag.setValor(pagamento.getValor());
		pag.setFixo(pagamento.isFixo());
		manager.merge(pag);
	}

	public void statusPagamento(Pagamento pagamento) {
		Pagamento pag = getPagamento(pagamento.getIdPagamento(), pagamento.getUsuario().getIdUsuario());
		pag.setStatusPagamento(pagamento.getStatusPagamento());
		manager.merge(pag);
	}
	
	public List<Pagamento> checarPagamentoDoDia(Date dataAtual, Long idUsuario) {
		return manager.createQuery(
				"select p from br.com.jsa.model.Pagamento p where p.usuario.idUsuario = :idUsuario and p.fixo = true and p.dataPagamento = :dataPagamento", Pagamento.class)
				.setParameter("dataPagamento", dataAtual)
				.setParameter("idUsuario", idUsuario)
				.getResultList();
	}
	
	public void fecharPagamento(Long idPagamento, Long idUsuario) {
		Pagamento pagamento = this.getPagamento(idPagamento, idUsuario);
		pagamento.setStatusPagamento(StatusPagamento.RECEBIDO);
		manager.merge(pagamento);
	}
	
	public void abrirPagamento(Long idPagamento, Long idUsuario) {
		Pagamento pagamento = this.getPagamento(idPagamento, idUsuario);
		pagamento.setStatusPagamento(StatusPagamento.ARECEBER);
		manager.merge(pagamento);
	}
	
	public List<Pagamento> pagamentosDoMes(Long idUsuario, List<Date> datas){
		List<Pagamento> pagamentosDoMes = manager.createQuery(
				"select p from br.com.jsa.model.Pagamento as p where p.usuario.idUsuario = :idUsuario and p.dataPagamento between :primeiroDia and :ultimoDia", Pagamento.class)
				.setParameter("idUsuario", idUsuario)
				.setParameter("primeiroDia",datas.get(0))
				.setParameter("ultimoDia", datas.get(1))
				.getResultList();
		return pagamentosDoMes;
	}
}
