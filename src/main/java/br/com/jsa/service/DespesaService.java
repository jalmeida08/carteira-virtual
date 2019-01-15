package br.com.jsa.service;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.com.jsa.model.Despesa;
import br.com.jsa.model.FinanciamentoBancario;
import br.com.jsa.model.Parcela;
import br.com.jsa.model.Pessoa;
import br.com.jsa.model.StatusDespesa;
import br.com.jsa.repository.DespesaRespository;
import br.com.jsa.repository.FinanciamentoBancarioRepository;
import br.com.jsa.repository.PessoaRepository;
import br.com.jsa.util.TokenUsuarioLogado;

@Stateless
public class DespesaService {
	
	@Inject
	private DespesaRespository despesaRespository;
	
	@Inject
	private PessoaRepository pessoaRepository;
	
	@Inject
	private FinanciamentoBancarioRepository financiamentoBancarioRepository;

	@Inject
	private TokenUsuarioLogado tokenUsuarioLogado;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void salvar(Despesa despesa) {
		List<Parcela> parcelas = despesa.getParcela();
		despesa.setParcela(null);
		
		if(despesa.getPessoa().getIdPessoa() != null) {
			despesa.setFinanciamentoBancario(null);
			Pessoa pessoa = pessoaRepository.getPessoa(despesa.getPessoa().getIdPessoa());
			despesa.setPessoa(pessoa);			
		} else {
			despesa.setPessoa(null);
			FinanciamentoBancario financiamentoBancario =
					financiamentoBancarioRepository.getFinanciamentoBancario(despesa.getFinanciamentoBancario().getIdFinanciamentoBancario());
			despesa.setFinanciamentoBancario(financiamentoBancario);
		}
		despesaRespository.salvar(despesa);
		
		if(parcelas.size() > 0) {
			System.out.println("despesa.getIdDespesa() "+ despesa.getIdDespesa());
			Despesa detachDespesa = despesaRespository.detachDespesa(despesa);
			for (Parcela parcela : parcelas) {
				parcela.setDespesa(detachDespesa);
			}
			despesa.setParcela(parcelas);
			despesaRespository.atualizar(despesa);
		}
	}
	
	public Despesa getDespesa(Long idDespesa) {
		Long idUsuario = tokenUsuarioLogado.recuperarIdUsuarioLogado();
		return despesaRespository.getDespesa(idDespesa, idUsuario);
	}
	
	public List<Despesa> listarTodasDespesas() {
		return despesaRespository.listarTodasDespesaUsuarios(1L);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Despesa pagarDespesa(Long idDespesa, Date dataPagamento) {
		Despesa despesa = getDespesa(idDespesa);
		despesa.setDataPagamento(dataPagamento);
		despesa.setStatusDespesa(StatusDespesa.PAGO);
		return despesaRespository.pagarDespesa(despesa);
		
	}

}
