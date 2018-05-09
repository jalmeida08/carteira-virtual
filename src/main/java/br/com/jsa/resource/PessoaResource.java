package br.com.jsa.resource;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.jsa.model.Pessoa;
import br.com.jsa.service.PessoaService;

@Path("/pessoa")
@RequestScoped
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class PessoaResource implements Serializable{

	private static final long serialVersionUID = 3888369111153629566L;
	
	@Inject
	private PessoaService pessoaService;

	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/{id}")
	public Pessoa getPessoa(@PathParam("id") Long id) {
		System.out.println(("ESTE È O ID DA CHAMADA: " + id));
		return pessoaService.getPessoa(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response salvar(Pessoa pessoa) {
		pessoaService.salvar(pessoa);
		URI uri = URI.create("pessoa/" + pessoa.getIdPessoa());
		return Response.created(uri).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/")
	public List<Pessoa> pessoas() {
		return pessoaService.buscarPessoas();
	}

	@DELETE
	@Path("/{id}")
	public Response deletar(@PathParam("id") Long idPessoa) {
		pessoaService.remover(idPessoa);
		return Response.ok().build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/buscarNomeDataNascimento")
	public Pessoa buscarNomeDataNascimento(Pessoa pessoa) {
		return pessoaService.buscarNomeDataNascimento(pessoa);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response atualizar(Pessoa pessoa) {
		pessoaService.atualizar(pessoa);
		return Response.accepted().build();
		
	}
}
