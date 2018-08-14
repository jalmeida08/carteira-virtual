package br.com.jsa.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.com.jsa.model.Parcela;
import br.com.jsa.repository.ParcelaRepository;

@Stateless
public class ParcelaService {

	@Inject
	private ParcelaRepository parcelaRepository;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void salvar(Parcela parcela) {
		parcelaRepository.salvar(parcela);
	}

	public Parcela getParcela(Long idPagamento) {
		return parcelaRepository.getParcela(idPagamento);
	}

	public List<Parcela> buscarTodasParcelas() {
		return parcelaRepository.buscarTodasParcelas();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualizar(Parcela parcela) {
		parcelaRepository.atualizar(parcela);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remover(Long idParcela) {
		parcelaRepository.remover(idParcela);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void fecharParcela(Parcela parcela) {
		Parcela p = this.getParcela(parcela.getIdParcela());
		p.setValorPago(parcela.getValorPago());
		p.setDataPagamento(parcela.getDataPagamento());
		this.parcelaRepository.fecharParcela(p);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void abrirParcela(Parcela parcela) {
		Parcela p = this.getParcela(parcela.getIdParcela());
		p.setValorPago(null);
		p.setDataPagamento(null);
		this.parcelaRepository.abrirParcela(p);
	}

}
