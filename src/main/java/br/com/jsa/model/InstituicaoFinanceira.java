package br.com.jsa.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity(name="instituicao_financeira")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idInstituicaoFinanceira", scope=InstituicaoFinanceira.class)
public class InstituicaoFinanceira implements Serializable{

	private static final long serialVersionUID = 8384127751280559407L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_instituicao_financeira")
	private Long idInstituicaoFinanceira;

	private String nome;
	
	@OneToMany(mappedBy="instituicaoFinanceira")
	private List<FinanciamentoBancario> financiamentoBancario = new ArrayList<FinanciamentoBancario>();
	
	@Version
	private Long versao;

	public Long getIdInstituicaoFinanceira() {
		return idInstituicaoFinanceira;
	}

	public void setIdInstituicaoFinanceira(Long idInstituicaoFinanceira) {
		this.idInstituicaoFinanceira = idInstituicaoFinanceira;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<FinanciamentoBancario> getFinanciamentoBancario() {
		return financiamentoBancario;
	}

	public void setFinanciamentoBancario(List<FinanciamentoBancario> financiamentoBancario) {
		this.financiamentoBancario = financiamentoBancario;
	}

	public Long getVersao() {
		return versao;
	}

	public void setVersao(Long versao) {
		this.versao = versao;
	}

	
}
