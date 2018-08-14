package br.com.jsa.resource;

import java.io.Serializable;
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

import br.com.jsa.model.Parcela;
import br.com.jsa.service.ParcelaService;

@Path("/parcela")
@RequestScoped
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class ParcelaResource implements Serializable {
	private static final long serialVersionUID = -4916593477326713846L;
	@Inject
	private ParcelaService parcelaService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response salvar(Parcela parcela) {
		return Response.ok().build();
	
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/{id}")
	public Parcela getParcela(@PathParam("id") Long idPagamento) {
		return parcelaService.getParcela(idPagamento);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/")
	public List<Parcela> buscarTodasParcelas() {
		return parcelaService.buscarTodasParcelas();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response atualizar(Parcela parcela) {
		parcelaService.atualizar(parcela);
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response remover(@PathParam("id") Long idParcela) {
		parcelaService.remover(idParcela);
		return Response.ok().build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/pagarParcela")
	public Response fecharParcela(Parcela parcela) {
		parcelaService.fecharParcela(parcela);
		return Response.ok().build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/abrirParcela")
	public Response abrirParcela(Parcela parcela) {
		parcelaService.abrirParcela(parcela);
		return Response.ok().build();
	}
}
