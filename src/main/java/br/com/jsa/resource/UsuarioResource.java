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

import br.com.jsa.model.Usuario;
import br.com.jsa.model.UsuarioLogado;
import br.com.jsa.service.UsuarioService;
import br.com.jsa.util.JWTUtil;

@Path("/usuario")
@RequestScoped
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class UsuarioResource implements Serializable {

	private static final long serialVersionUID = -5514888143532451473L;
	@Inject
	private UsuarioService usuarioService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response salvar(Usuario usuario) {
		usuarioService.salvar(usuario);
		URI uri = URI.create("usuario/" + usuario.getIdUsuario());
		return Response.created(uri).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Usuario getUsuario(@PathParam("id") Long idUsuario) {
		System.out.println(idUsuario);
		return usuarioService.getUsuario(idUsuario);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/login")
	public Response logar(Usuario usuario) {
		
		String user = usuarioService.logar(usuario);
		String token = JWTUtil.create(user);
		
		if (user != null) {
			UsuarioLogado me = new UsuarioLogado();
			me.setToken(token);
			return Response.ok().entity(me).build();
		}
		
		return Response.status(Response.Status.UNAUTHORIZED).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public List<Usuario> buscarUsuarios() {
		return usuarioService.buscarUsuarios();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response atualizar(Usuario usuario) {
		usuarioService.atualizar(usuario);
		return Response.ok().build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/atualizarSenha")
	public Response atualizarSenha(Usuario usuario) {
		usuarioService.atualizarSenha(usuario);
		return Response.ok().build();
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response remover(@PathParam("id") Long idUsuario) {
		usuarioService.remover(idUsuario);
		return Response.ok().build();
	}
}
