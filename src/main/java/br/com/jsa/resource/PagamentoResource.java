package br.com.jsa.resource;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.jsa.model.Pagamento;
import br.com.jsa.service.PagamentoService;

@Path("/pagamento")
public class PagamentoResource implements Serializable{

	private static final long serialVersionUID = -3212001621328765794L;

	private PagamentoService pagamentoService;
	
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/{id}")
	public Pagamento getPessoa(@PathParam("id") Long idPagamento) {
		System.out.println(("ESTE È O ID DA CHAMADA: " + idPagamento));
		return pagamentoService.getPagamento(idPagamento);
	}

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public List<Pagamento> pagamentos(){
		return pagamentoService.buscarPagamentos();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response salvar(Pagamento pagamento) {
		pagamentoService.salvar(pagamento);
		URI uri = URI.create("pessoa/" + pagamento.getIdPagamento());
		return Response.created(uri).build();
	}
	
}
