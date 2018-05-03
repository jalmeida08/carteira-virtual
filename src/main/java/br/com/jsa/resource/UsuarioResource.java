package br.com.jsa.resource;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.jsa.model.Usuario;
import br.com.jsa.service.UsuarioService;

@Path("/usuario")
@RequestScoped
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class UsuarioResource  implements Serializable{

	private static final long serialVersionUID = -5514888143532451473L;
	@Inject
	private UsuarioService usuarioService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response salvar(Usuario usuario) {
		System.out.println("__________________ ID "+usuario.getEmail() + "  "+ usuario.getPessoa().getIdPessoa());
		usuarioService.salvar(usuario);
		URI uri = URI.create("usuario/" + usuario.getIdUsuario());
		return Response.created(uri).build();
	}

/*	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Usuario getUsuario(@PathParam("id") Long idUsuario) {
		return usuarioService.getUsuario(idUsuario);
	}*/

	public Usuario logar(String email, String senha) {
		return usuarioService.logar(email, senha);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public List<Usuario> buscarUsuarios() {
		return usuarioService.buscarUsuarios();
	}

/*	public void atualizar(Usuario usuario) {
		usuarioService.atualizar(usuario);
	}

	public void atualizarSenha(Usuario usuario) {
		usuarioService.atualizarSenha(usuario);
	}

	public void remover(Long idUsuario) {
		usuarioService.remover(idUsuario);
	}*/
}
