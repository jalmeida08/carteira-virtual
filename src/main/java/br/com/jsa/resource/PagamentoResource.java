package br.com.jsa.resource;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

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

import br.com.jsa.model.Pagamento;
import br.com.jsa.service.PagamentoService;

@Path("/pagamento")
public class PagamentoResource implements Serializable {

	private static final long serialVersionUID = -3212001621328765794L;

	@Inject
	private PagamentoService pagamentoService;

	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/buscarPagamento/{idUsuario}/{idpagamento}")
	public Pagamento getPagamento(
			@PathParam("idpagamento") Long idPagamento, @PathParam("idUsuario")Long idUsuario) {
		return pagamentoService.getPagamento(idPagamento);
	}

	@GET
	@Path("/buscarPagamentosUsuario/{idUsuario}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public List<Pagamento> pagamentos() {
		return pagamentoService.buscarTodosPagamentosUsuario();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response salvar(Pagamento pagamento) {
		pagamentoService.salvar(pagamento);
		URI uri = URI.create("pessoa/" + pagamento.getIdPagamento());
		return Response.created(uri).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response atualizar(Pagamento pagamento) {
		pagamentoService.atualizar(pagamento);
		return Response.ok().build();
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/remover/{idUsuario}/{idPagamento}")
	public Response remover(
			@PathParam("idUsaurio")Long idUsuario,@PathParam("idPagamento") Long idPagamento) {
		pagamentoService.remover(idPagamento);
		return Response.ok().build();
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/fecharPagamento/{idUsuario}/{idPagamento}")
	public Response fecharPagamento(
			@PathParam("idUsuario")Long idUsuario, @PathParam("idPagamento") Long idPagamento) {
		try{
			pagamentoService.fecharPagamento(idPagamento);
			return Response.ok().build();
		}catch (RuntimeException e) {
			return Response.serverError().build();
		}
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/abrirPagamento/{idUsuario}/{idPagamento}")
	public Response abrirPagamento(
			@PathParam("idUsuario")Long idUsuario, @PathParam("idPagamento") Long idPagamento) {
		try{
			pagamentoService.abrirPagamento(idPagamento, idUsuario);
			return Response.ok().build();
		}catch (RuntimeException e) {
			return Response.serverError().build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/pagametosDoMes")
	public List<Pagamento> pagamentosDoMes() {
		return pagamentoService.buscarTodosOsPagamentosDoMes();
	}

}
