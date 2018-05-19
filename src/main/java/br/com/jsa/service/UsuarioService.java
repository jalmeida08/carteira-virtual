package br.com.jsa.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.NoResultException;

import br.com.jsa.model.Usuario;
import br.com.jsa.repository.UsuarioRepository;

@Stateless
public class UsuarioService {

	@Inject
	private UsuarioRepository usuarioRepository;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void salvar(Usuario usuario) {
		usuarioRepository.salvar(usuario);
	}

	public Usuario getUsuario(Long idUsuario) {
		return usuarioRepository.getUsuario(idUsuario);
	}

	public Usuario logar(Usuario usuario) {
		try {
			return usuarioRepository.logar(usuario.getEmail(), usuario.getSenha());
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Usuario> buscarUsuarios() {
		return usuarioRepository.buscarUsuarios();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualizar(Usuario usuario) {
		usuarioRepository.atualizar(usuario);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualizarSenha(Usuario usuario) {
		usuarioRepository.atualizarSenha(usuario);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remover(Long idUsuario) {
		usuarioRepository.remover(idUsuario);
	}
}
