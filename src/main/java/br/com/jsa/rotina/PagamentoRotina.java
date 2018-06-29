package br.com.jsa.rotina;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import br.com.jsa.service.PagamentoService;

@Singleton
@Startup
public class PagamentoRotina {
	@Inject
	private PagamentoService pagamentoService;
	
	@Schedule(hour="*", minute="*/1")
	public void VerificarPagamentos() {
		pagamentoService.buscarTodosOsPagamentosDoMes();
		pagamentoService.checarPagamentoDoDia();
	}

}
