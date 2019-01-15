package br.com.jsa.util;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public class TokenUsuarioLogado {
	
	@Inject
	private HttpServletRequest req;
	
	private String recuperarTokenHead() {
		String token = req.getHeader(JWTUtil.TOKEN_HEADER);
	    Jws<Claims> decode = JWTUtil.decode(token);
	    System.out.println("decode " + decode);
	    return decode.toString();
	}
	
	public Long recuperarIdUsuarioLogado() {
		String arrayToken = recuperarTokenHead();
		arrayToken.toString().split("#");
	    return 1L;
	}
}
