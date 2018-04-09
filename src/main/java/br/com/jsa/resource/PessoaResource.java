package br.com.jsa.resource;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.jsa.model.Pessoa;
import br.com.jsa.util.PessoaService;

@Path("/pessoa")
@RequestScoped
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class PessoaResource implements Serializable{

	private static final long serialVersionUID = 1479679706442678577L;

	@Inject
	private PessoaService pessoaService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/{id}")
	public Pessoa getPessoa(@PathParam("id") Long id) {
		System.out.println(("ESTE È O ID DA CHAMADA: "+ id));
		return pessoaService.getPessoa(id);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/")
	public void salvar(Pessoa pessoa) {
		pessoaService.salvar(pessoa);
	}
}
