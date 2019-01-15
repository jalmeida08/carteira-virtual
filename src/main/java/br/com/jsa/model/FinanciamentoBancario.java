package br.com.jsa.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity(name="financiamento_bancario")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idFinanciamentoBancario", scope=FinanciamentoBancario.class)
public class FinanciamentoBancario implements Serializable{

	private static final long serialVersionUID = -8809020076065320425L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_financiamento_bancario")
	private Long idFinanciamentoBancario;
	
	private Long numero;
	
	private String bandeira;
	
	@ManyToOne
	@JoinColumn(name="id_instituicao_financeira")
	private InstituicaoFinanceira instituicaoFinanceira;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_vencimento")
	private Date dataVecimento;
	
	@OneToMany(mappedBy="financiamentoBancario", orphanRemoval = false, cascade = {CascadeType.ALL})
	private List<Despesa> despesa = new ArrayList<Despesa>();
	
	@Version
	private Long versao;

	public Long getIdFinanciamentoBancario() {
		return idFinanciamentoBancario;
	}

	public void setIdFinanciamentoBancario(Long idFinanciamentoBancario) {
		this.idFinanciamentoBancario = idFinanciamentoBancario;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public InstituicaoFinanceira getInstituicaoFinanceira() {
		return instituicaoFinanceira;
	}

	public void setInstituicaoFinanceira(InstituicaoFinanceira instituicaoFinanceira) {
		this.instituicaoFinanceira = instituicaoFinanceira;
	}

	public Date getDataVecimento() {
		return dataVecimento;
	}

	public void setDataVecimento(Date dataVecimento) {
		this.dataVecimento = dataVecimento;
	}

	public Long getVersao() {
		return versao;
	}

	public void setVersao(Long versao) {
		this.versao = versao;
	}

	public List<Despesa> getDespesa() {
		return despesa;
	}

	public void setDespesa(List<Despesa> despesa) {
		this.despesa = despesa;
	}

	public String getBandeira() {
		return bandeira;
	}

	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}

}
