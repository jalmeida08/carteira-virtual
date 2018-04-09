package br.com.jsa.util;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.com.jsa.model.Pessoa;
import br.com.jsa.repository.PessoaRepository;

@Stateless
public class PessoaService {
	
	@Inject
	private PessoaRepository pessoaRepository;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void salvar(Pessoa pessoa) {
		pessoaRepository.salvar(pessoa);
	}
	
	public Pessoa getPessoa(Long idPessoa) {
		return pessoaRepository.getPessoa(idPessoa);
	}

	public List<Pessoa> buscarPessoas(){
		return pessoaRepository.buscarPessoas();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualizar(Pessoa pessoa) {
		pessoaRepository.atualizar(pessoa);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remover(Pessoa pessoa) {
		pessoaRepository.remover(pessoa);
	}
}
