package br.com.jsa.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.com.jsa.model.FinanciamentoBancario;
import br.com.jsa.repository.FinanciamentoBancarioRepository;

@Stateless
public class FinanciamentoBancarioService {
	
	
	@Inject
	private FinanciamentoBancarioRepository financiamentoBancarioRepository;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void salvar(FinanciamentoBancario financiamentoBancario) {
		financiamentoBancarioRepository.salvar(financiamentoBancario);
	}
		
	public FinanciamentoBancario getFinanciamentoBancario(Long idFinanciamentoBancario) {
		return financiamentoBancarioRepository.getFinanciamentoBancario(idFinanciamentoBancario);
	}
	
	public List<FinanciamentoBancario> listaFinanciamentosBancarios(){
		return financiamentoBancarioRepository.listaFinanciamentosBancarios();
	} 
}
