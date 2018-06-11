package br.com.jsa.service;

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
		//CHECA SE A PESSOA JA EXISTE NO BANCO DE DADOS
		if (pessoaRepository.buscarNome(pessoa).size() == 0) {
			pessoaRepository.salvar(pessoa);
		}else{
			throw new RuntimeException("Nao pode gravar pessoa");			
		}
	}

	public Pessoa getPessoa(Long idPessoa) {
		return pessoaRepository.getPessoa(idPessoa);
	}

	public List<Pessoa> buscarPessoas() {
		return pessoaRepository.buscarPessoas();
	}

	public Pessoa buscarNome(Pessoa pessoa) {
		if (pessoaRepository.buscarNome(pessoa).size() <= 1) {
			return pessoaRepository.buscarNome(pessoa).get(0);
		}
		return null;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualizar(Pessoa pessoa) {
		pessoaRepository.atualizar(pessoa);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remover(Long idPessoa) {
		pessoaRepository.remover(idPessoa);
	}
}
