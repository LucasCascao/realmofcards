package br.edu.les.realmofcard.strategy.troca;

import br.edu.les.realmofcard.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.les.realmofcard.domain.*;
import br.edu.les.realmofcard.strategy.email.troca.EnviaEmailTrocaCodigoRastreio;
import br.edu.les.realmofcard.dao.RastreioDAO;
import br.edu.les.realmofcard.util.GeradorCodigo;

@Component
public class GeraCodigoRastreioTroca implements IStrategy {

	@Autowired
	private RastreioDAO rastreioDAO;

	@Autowired
	private EnviaEmailTrocaCodigoRastreio enviaEmailTrocaCodigoRastreio;

	@Override
	public String processar(final EntidadeDominio entidade) {

		if(entidade instanceof Transicao){

			Transicao transicao = (Transicao) entidade;

			if(transicao.getPedido() != null
				&& transicao.getPedido().getStatusPedido() != null
				&& transicao.getPedido().getStatusPedido().getId().equals(9)){

				Rastreio rastreio = new Rastreio();

				rastreio.setCodigoRastreio(GeradorCodigo.gerarCodigoRastreio());
				rastreio = (Rastreio) rastreioDAO.salvar(rastreio);
				transicao.setRastreio(rastreio);
				enviaEmailTrocaCodigoRastreio.processar(transicao);
			}
		}

		return null;
	}
}
