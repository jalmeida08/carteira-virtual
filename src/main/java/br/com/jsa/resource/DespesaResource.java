package br.com.jsa.resource;

import java.io.Serializable;
import java.util.Date;
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

import br.com.jsa.model.Despesa;
import br.com.jsa.service.DespesaService;

@Path("/despesa")
public class DespesaResource implements Serializable {

	private static final long serialVersionUID = -3212001621328765794L;

	@Inject
	private DespesaService despesaService;


	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response salvar(Despesa despesa) {
		despesaService.salvar(despesa);
		return Response.ok().build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("buscarDespesasUsuario/")
	public List<Despesa> listarTodasDespesas() {
		return despesaService.listarTodasDespesas();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/buscarDespesa/{idDespesa}")
	public Despesa getDespesa(
			@PathParam("idDespesa") Long idDespesa) {
		return despesaService.getDespesa(idDespesa);
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/pagarDespesa/{idDespesa}/{dataPagamento}")
	public void pagarDespesa(
			@PathParam("idDespesa") Long idDespesa,
			@PathParam("dataPagamento")Date dataPagamento)  {
		Despesa despesa = despesaService.pagarDespesa(idDespesa, dataPagamento);
		Response.ok().entity(despesa).build();
	}

}
