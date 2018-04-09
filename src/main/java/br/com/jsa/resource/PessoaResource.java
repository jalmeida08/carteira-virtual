package br.com.jsa.resource;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.jsa.model.Pessoa;

@Path("/pessoa")
@RequestScoped
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class PessoaResource implements Serializable{

	private static final long serialVersionUID = 1479679706442678577L;

	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/teste")
	public Pessoa pessoa() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Jefferson");
		return pessoa;
	}
}
