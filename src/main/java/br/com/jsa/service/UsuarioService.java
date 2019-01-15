package br.com.jsa.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;

import br.com.jsa.model.Usuario;
import br.com.jsa.repository.UsuarioRepository;
import br.com.jsa.util.TokenUsuarioLogado;
import br.com.jsa.util.JWTUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

@Stateless
public class UsuarioService {
	
	@Inject
	private UsuarioRepository usuarioRepository;
	
	@Inject
	private TokenUsuarioLogado getUsuarioLogado;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void salvar(Usuario usuario) {
		usuarioRepository.salvar(usuario);
	}

	public Usuario getUsuario(Long idUsuario) {
		return usuarioRepository.getUsuario(idUsuario);
	}

	public String logar(Usuario usuario) {
	    
		try {
			Usuario user = usuarioRepository.logar(usuario.getEmail(), usuario.getSenha());
			
			SimpleDateFormat horaSessao = new SimpleDateFormat("dd/MM/yyyy#HH:MM:ss");
 			String serialToken = user.getIdUsuario()+"#"
					+user.getEmail()+"#"
					+horaSessao.format(new Date());
			return serialToken;
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Usuario> buscarUsuarios() {
		getUsuarioLogado.recuperarIdUsuarioLogado();
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
