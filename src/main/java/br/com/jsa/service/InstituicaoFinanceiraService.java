package br.com.jsa.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.com.jsa.model.InstituicaoFinanceira;
import br.com.jsa.repository.InstituicaoFinanceiraRepository;

@Stateless
public class InstituicaoFinanceiraService {

	@Inject
	private InstituicaoFinanceiraRepository instituicaoFinanceiraRepository;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void salvar(InstituicaoFinanceira instituicaoFinanceira) {
		instituicaoFinanceiraRepository.salvar(instituicaoFinanceira);
	}
		
	public InstituicaoFinanceira getInstituicaoFinanceira(Long idInstituicaoFinanceira) {
		return instituicaoFinanceiraRepository.getInstituicaoFinanceira(idInstituicaoFinanceira);
	}
	
	public List<InstituicaoFinanceira> listaInstituicoesFinanceiras(){
		return instituicaoFinanceiraRepository.instituicoesFinanceiras();
	} 
}
