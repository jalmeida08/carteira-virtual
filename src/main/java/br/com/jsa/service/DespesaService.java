package br.com.jsa.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.com.jsa.model.Despesa;
import br.com.jsa.model.Parcela;
import br.com.jsa.model.Pessoa;
import br.com.jsa.repository.DespesaRespository;
import br.com.jsa.repository.PessoaRepository;

@Stateless
public class DespesaService {
	@Inject
	private DespesaRespository despesaRespository;
	@Inject
	private PessoaRepository pessoaRepository;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void salvar(Despesa despesa) {

		Pessoa pessoa = pessoaRepository.getPessoa(despesa.getPessoa().getIdPessoa());
		
		List<Parcela> parcelas = despesa.getParcela();
		despesa.setParcela(null);
		despesa.setPessoa(pessoa);
		despesaRespository.salvar(despesa);
		
		if(parcelas.size() > 0) {
			Despesa detachDespesa = despesaRespository.detachDespesa(despesa);
			for (Parcela parcela : parcelas) {
				parcela.setDespesa(detachDespesa);
			}
			despesa.setParcela(parcelas);
			despesaRespository.atualizar(despesa);
		}
	}
	
	public Despesa getDespesa(Long idDespesa) {
		return despesaRespository.getDespesa(idDespesa);
	}
	
	public List<Despesa> listarTodasDespesas() {
		return despesaRespository.listarTodasDespesas();
	}
	
}
