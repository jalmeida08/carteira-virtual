package br.com.jsa.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity(name = "pessoa")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idPessoa")
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 4022370808231779273L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pessoa")
	private Long idPessoa;
	private String nome;
	@Column(name = "data_nascimento")
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	@OneToOne(mappedBy = "pessoa", orphanRemoval = true)
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	@OneToMany(mappedBy="pessoa")
	private List<Pagamento> pagamento = new ArrayList<Pagamento>();
	@Version
	private Long versao;

	public Long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
