package br.com.jsa.repository;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.jsa.model.Despesa;

public class DespesaRespository {

	@Inject
	private EntityManager manager;

	public Despesa getDespesa(Long idDespesa, Long idUsuario) {
		return manager.createQuery(
				"from br.com.jsa.model.Despesa d where d.idUsuario = :idUsuario and d.idDespesa = :idDespesa", Despesa.class)
				.setParameter("idUsuario", idUsuario)
				.setParameter("idDespesa", idDespesa)
				.getSingleResult();
	}

	public void salvar(Despesa despesa) {
		manager.persist(despesa);
	}

	public Despesa detachDespesa(Despesa despesa) {
		manager.detach(despesa);
		return despesa;
	}

	public void atualizar(Despesa despesa) {
		manager.merge(despesa);
	}

	public List<Despesa> listarTodasDespesaUsuarios(Long idUsuario) {
		return manager.createQuery(
				"select d from br.com.jsa.model.Despesa d where d.idUsuario = :idUsuario", Despesa.class)
				.setParameter("idUsuario", idUsuario)
				.getResultList();
	}

	public Despesa pagarDespesa(Despesa despesa) {
		return manager.merge(despesa);
	}

	public List<Despesa> buscarDespesaUsuarioPorPeriodo(Long idUsuario, Date dataInicio, Date dataFim) {
		return manager.createQuery(
				"select d from br.com.br.sa.model.Despesa d where d.idUsuario = :idUsuario and between d.data_vencimento = :datainicio and :dataFim",
				Despesa.class)
				.setParameter("idUsuario", idUsuario)
				.setParameter("dataInicio", dataInicio)
				.setParameter("dataFim", dataFim)
				.getResultList();
	}

}
