package br.com.jsa.resource;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.jsa.model.InstituicaoFinanceira;
import br.com.jsa.service.InstituicaoFinanceiraService;

@Path("/instituicaoFinanceira")
public class InstituicaoFinanceiraResource implements Serializable{
	
	private static final long serialVersionUID = -4540674346207399051L;
	
	@Inject
	private InstituicaoFinanceiraService instituicaoFinanceiraService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response salvar(InstituicaoFinanceira instituicaoFinanceira) {
		instituicaoFinanceiraService.salvar(instituicaoFinanceira);
		URI uri = URI.create("despesa/" + instituicaoFinanceira.getIdInstituicaoFinanceira());
		return Response.created(uri).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public List<InstituicaoFinanceira> listaInstituicoesFinanceiras() {
		return instituicaoFinanceiraService.listaInstituicoesFinanceiras();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public InstituicaoFinanceira getInstituicaoFinanceira(@PathParam("id") Long idInstituicaoFinanceira) {
		return instituicaoFinanceiraService.getInstituicaoFinanceira(idInstituicaoFinanceira);
	}
}
