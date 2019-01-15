package br.com.jsa.model;

import java.io.Serializable;

public class UsuarioLogado implements Serializable{
	
	private static final long serialVersionUID = 849706406476904157L;
	
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
