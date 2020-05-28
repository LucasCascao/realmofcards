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

		if(entidade instanceof Troca){

			Troca troca = (Troca) entidade;

			if(troca.getPedidoParaTroca() != null
				&& troca.getPedidoParaTroca().getStatusPedido() != null
				&& troca.getPedidoParaTroca().getStatusPedido().getId().equals(9)){

				Rastreio rastreio = new Rastreio();

				rastreio.setCodigoRastreio(GeradorCodigo.gerarCodigoRastreio());
				rastreio = (Rastreio) rastreioDAO.salvar(rastreio);
				troca.setRastreio(rastreio);
				enviaEmailTrocaCodigoRastreio.processar(troca);
			}
		}

		return null;
	}
}
