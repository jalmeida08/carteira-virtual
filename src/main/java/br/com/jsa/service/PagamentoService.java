package br.com.jsa.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.com.jsa.model.Pagamento;
import br.com.jsa.model.StatusPagamento;
import br.com.jsa.repository.PagamentoRespository;

@Stateless
public class PagamentoService {
	@Inject
	private PagamentoRespository pagamentoRespository;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void salvar(Pagamento pagamento) {
		if(pagamento.isFixo()) {
			pagamento.setStatusPagamento(StatusPagamento.ARECEBER);
		}
		pagamentoRespository.salvar(pagamento);
	}
	
	public Pagamento getPagamento(Long idPagamento) {
		return pagamentoRespository.getPagamento(idPagamento);
	}
	
	public List<Pagamento>buscarPagamentos() {
		return pagamentoRespository.buscarTodosPagamentos();
	}
	
	public void buscarTodosOsPagamentosDoMes(){
		Date data = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		System.out.println(new SimpleDateFormat("dd/MM/yyyy").format(c.getTime()));
		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualizar(Pagamento pagamento) {
		if(pagamento.isFixo()) {
			pagamento.setStatusPagamento(StatusPagamento.ARECEBER);
		}
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
	
	public void checarPagamentoDoDia() {
		Date dataAtual = new Date();
		List<Pagamento> checarPagamentoDoDia = pagamentoRespository.checarPagamentoDoDia(dataAtual);
		for (Pagamento pagamento : checarPagamentoDoDia) {
			System.out.println(pagamento.getStatusPagamento());
			if(pagamento.getStatusPagamento().equals(StatusPagamento.ARECEBER)) {
				pagamento.setStatusPagamento(StatusPagamento.RECEBIDO);
				pagamentoRespository.atualizar(pagamento);
			}
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void fecharPagamento(Long idPagamento) {
		Pagamento pagamento = this.getPagamento(idPagamento);
		if(pagamento.getStatusPagamento().equals(StatusPagamento.RECEBIDO)) {
			throw new RuntimeException("Pagamento já está com o status de recebido");
		}
		pagamentoRespository.fecharPagamento(idPagamento);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void abrirPagamento(Long idPagamento) {
		Pagamento pagamento = this.getPagamento(idPagamento);
		if(pagamento.getStatusPagamento().equals(StatusPagamento.ARECEBER)) {
			throw new RuntimeException("Pagamento já está com o status de aberto");
		}
		pagamentoRespository.abrirPagamento(idPagamento);
	}
}
