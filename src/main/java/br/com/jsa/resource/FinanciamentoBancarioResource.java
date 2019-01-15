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

import br.com.jsa.model.FinanciamentoBancario;
import br.com.jsa.service.FinanciamentoBancarioService;

@Path("/financiamentoBancario")
public class FinanciamentoBancarioResource implements Serializable{

	private static final long serialVersionUID = -8280572728422974801L;
	
	@Inject
	private FinanciamentoBancarioService financiamentoBancarioService;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response salvar(FinanciamentoBancario financiamentoBancario) {
		financiamentoBancarioService.salvar(financiamentoBancario);
		URI uri = URI.create("Finaciamentobancario/"+financiamentoBancario.getIdFinanciamentoBancario());
		return Response.created(uri).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public List<FinanciamentoBancario> listarFinanciamentosBancarios() {
		return financiamentoBancarioService.listaFinanciamentosBancarios();
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public FinanciamentoBancario getFinaiamentobancario(@PathParam("id") Long idFinanciamentoBancario) {
		return financiamentoBancarioService.getFinanciamentoBancario(idFinanciamentoBancario);
	}
}
