package br.com.jsa.repository;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.jsa.model.Parcela;

public class ParcelaRepository {
	
	@Inject
	private EntityManager manager;
	
	public void salvar(Parcela parcela) {
		this.manager.persist(parcela);
	}

	public Parcela getParcela(Long idPagamento) {
		return this.manager.find(Parcela.class, idPagamento);
	}
	
	public List<Parcela> buscarTodasParcelas() {
		return manager.createQuery("select p from br.com.jsa.model.Parcela p", Parcela.class).getResultList();
	}
	
	public void remover(Long idParcela) {
		Parcela parcela = getParcela(idParcela);
		manager.remove(parcela);
	}

	public void atualizar(Parcela parcela) {
		Parcela p = getParcela(parcela.getIdParcela());
		p.setDataPagamento(parcela.getDataPagamento());
		p.setDataVencimento(parcela.getDataVencimento());
		p.setPagamento(parcela.getPagamento());
		p.setValorPago(parcela.getValorPago());
		p.setValorParcela(parcela.getValorParcela());
		manager.merge(p);
	}

	public void fecharParcela(Parcela parcela) {
		manager.merge(parcela);
	}

	public void abrirParcela(Parcela parcela) {
		manager.merge(parcela);
	}
}
