package br.com.jsa.util;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.com.jsa.model.Usuario;
import br.com.jsa.service.UsuarioService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public class TokenUsuarioLogado {
	
	@Inject
	private HttpServletRequest req;
	
	@Inject
	private UsuarioService usuarioService;
	
	private String recuperarTokenHead() {
		String token = req.getHeader(JWTUtil.TOKEN_HEADER);
	    Jws<Claims> decode = JWTUtil.decode(token);
	    return decode.getBody().getSubject();
	}
	
	public Long recuperarIdUsuarioLogado() {
		String token = recuperarTokenHead();
		String[] arrayToken = token.toString().split("#");
	    return Long.parseLong(arrayToken[0]);
	}
	
	public Usuario recuperarObjectUsuario() {
		return usuarioService.getUsuario(recuperarIdUsuarioLogado());
	}
}
