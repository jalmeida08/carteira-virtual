package br.com.jsa.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.jsa.model.Pessoa;

public class PessoaRepository  implements Serializable{

	private static final long serialVersionUID = -7761370164651595939L;
	@Inject
	private EntityManager manager;
	
	public Pessoa getPessoa(Long idPessoa) {
		return manager.find(Pessoa.class, idPessoa);
	}
	
	public List<Pessoa> buscarPessoas() {
		return manager.createQuery("select p from br.com.jsa.model.Pessoa p",Pessoa.class).getResultList();		
	}
	
	public List<Pessoa> buscarNome(Pessoa pessoa) {
		return manager.createQuery("select p from br.com.jsa.model.Pessoa p where nome = :nome", Pessoa.class)
				.setParameter("nome", pessoa.getNome())
				.getResultList();
	}
	
	public void salvar(Pessoa pessoa) {
		manager.persist(pessoa);
	}
	
	public void remover(Long idPessoa) {
		Pessoa pessoa = getPessoa(idPessoa);
		manager.remove(pessoa);
	}
	
	public void atualizar(Pessoa pessoa) {
		Pessoa p = getPessoa(pessoa.getIdPessoa());
		p.setNome(pessoa.getNome());
		manager.merge(p);
	}
	
}
