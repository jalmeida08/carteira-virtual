package br.com.jsa.util;

import java.sql.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTUtil {
	
	private static String key = "SECRET_TOKN";
	
	public static final String TOKEN_HEADER = "Authentication";
	
	public static String create(String subject) {
		return Jwts.builder()
				.setSubject(subject)
				.signWith(SignatureAlgorithm.HS512, key)
				.setExpiration(new Date(System.currentTimeMillis() + 3600000))
				.compact();
	}
	
	public static Jws<Claims> decode(String token){
		return Jwts.parser().setSigningKey(key).parseClaimsJws(token);
	}
}
