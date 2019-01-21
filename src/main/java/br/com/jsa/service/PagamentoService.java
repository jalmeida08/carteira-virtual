package br.com.jsa.service;

import java.util.ArrayList;
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
import br.com.jsa.util.TokenUsuarioLogado;

@Stateless
public class PagamentoService {
	@Inject
	private PagamentoRespository pagamentoRespository;
	
	@Inject
	private TokenUsuarioLogado tokenUsuarioLogado;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void salvar(Pagamento pagamento) {
		pagamento.setUsuario(tokenUsuarioLogado.recuperarObjectUsuario());
		pagamentoRespository.salvar(pagamento);
	}

	public Pagamento getPagamento(Long idPagamento) {
		Long idUsuario = tokenUsuarioLogado.recuperarIdUsuarioLogado();
		return pagamentoRespository.getPagamento(idPagamento, idUsuario);
	}

	public List<Pagamento> buscarTodosPagamentosUsuario() {
		Long idUsuario = tokenUsuarioLogado.recuperarIdUsuarioLogado();
		return pagamentoRespository.buscarTodosPagamentosUsuario(idUsuario);
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
	public void remover(Long idPagamento) {
		Long idUsuario = tokenUsuarioLogado.recuperarIdUsuarioLogado();
		pagamentoRespository.remover(idPagamento, idUsuario);
	}

	public void checarPagamentoDoDia() {
		Date dataAtual = new Date();
		Long idUsuario = tokenUsuarioLogado.recuperarIdUsuarioLogado();
		List<Pagamento> checarPagamentoDoDia = pagamentoRespository.checarPagamentoDoDia(dataAtual, idUsuario);
		for (Pagamento pagamento : checarPagamentoDoDia) {
			System.out.println(pagamento.getStatusPagamento());
			if (pagamento.getStatusPagamento().equals(StatusPagamento.ARECEBER)) {
				pagamento.setStatusPagamento(StatusPagamento.RECEBIDO);
				pagamentoRespository.atualizar(pagamento);
			}
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void fecharPagamento(Long idPagamento) {
		Pagamento pagamento = this.getPagamento(idPagamento);
		if (pagamento.getStatusPagamento().equals(StatusPagamento.RECEBIDO)) {
			throw new RuntimeException("Pagamento já está com o status de recebido");
		}
		pagamentoRespository.fecharPagamento(idPagamento, idPagamento);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void abrirPagamento(Long idPagamento, Long idUsuario) {
		Pagamento pagamento = this.getPagamento(idPagamento);
		if (pagamento.getStatusPagamento().equals(StatusPagamento.ARECEBER)) {
			throw new RuntimeException("Pagamento já está com o status de aberto");
		}
		pagamentoRespository.abrirPagamento(idPagamento, idUsuario);
	}

	public List<Pagamento> buscarTodosOsPagamentosDoMes() {
		Date data = new Date();
		Long idUsuario = tokenUsuarioLogado.recuperarIdUsuarioLogado();
		return pagamentoRespository.pagamentosDoMes(idUsuario, this.primeiroEUltimoDia(data));			
	}

	public List<Date> primeiroEUltimoDia(Date data) {
		List<Date> primeiroEUltimoDiaDoMes = new ArrayList<Date>();
		Calendar primeiroDia = Calendar.getInstance();

		primeiroDia.setTime(data);
		primeiroDia.add(Calendar.MONTH, 0);
		primeiroDia.set(Calendar.DAY_OF_MONTH, primeiroDia.getActualMinimum(Calendar.DAY_OF_MONTH));
		primeiroEUltimoDiaDoMes.add(primeiroDia.getTime());

		Calendar ultimoDia = Calendar.getInstance();
		ultimoDia.setTime(data);
		ultimoDia.add(Calendar.MONTH, 0);
		ultimoDia.set(Calendar.DAY_OF_MONTH, ultimoDia.getActualMaximum(Calendar.DAY_OF_MONTH));
		primeiroEUltimoDiaDoMes.add(ultimoDia.getTime());
		return primeiroEUltimoDiaDoMes;
	}
}
