package br.com.jsa.repository;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.jsa.model.Pessoa;
import br.com.jsa.model.Usuario;

public class UsuarioRepository {

	@Inject
	private EntityManager manager;
	
	public void salvar(Usuario usuario) {
		Pessoa pessoa = getPessoa(usuario.getPessoa().getIdPessoa());
		usuario.setPessoa(pessoa);
		manager.persist(usuario);
	}
	
	public Usuario getUsuario(Long idUsuario) {
		return manager.find(Usuario.class, idUsuario);
	}
	
	public Pessoa getPessoa(Long idPessoa) {
		return manager.find(Pessoa.class, idPessoa);
	}
	
	public Usuario logar(String email, String senha) {
		return manager.createQuery("select u from br.com.jsa.model.Usuario u where u.email = :email and u.senha = :senha", Usuario.class)
				.setParameter("email", email)
				.setParameter("senha", senha)
				.getSingleResult();
	}
	
	public List<Usuario> buscarUsuarios() {
		return manager.createQuery("select u from br.com.jsa.model.Usuario u", Usuario.class).getResultList();
	}
	
	public void atualizar (Usuario usuario) {
		Usuario user = getUsuario(usuario.getIdUsuario());
		user.setEmail(usuario.getEmail());
		manager.persist(user);
	}
	
	public void atualizarSenha(Usuario usuario) {
		Usuario user = getUsuario(usuario.getIdUsuario());
		user.setSenha(usuario.getSenha());
		manager.persist(user);
	}
	
	public void remover(Long idUsuario) {
		Usuario usuario = getUsuario(idUsuario);
		manager.remove(usuario);
	}
}
