package br.com.cascao.realmofcard.negocio.strategy.troca;

import br.com.cascao.realmofcard.domain.*;
import br.com.cascao.realmofcard.negocio.strategy.IStrategy;
import br.com.cascao.realmofcard.negocio.strategy.email.EnviaEmailTrocaCodigoRastreio;
import br.com.cascao.realmofcard.persistence.RastreioPersistence;
import br.com.cascao.realmofcard.util.GeradorCodigo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GeraCodigoRastreio implements IStrategy{

	@Autowired
	private RastreioPersistence rastreioPersistence;

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
				rastreio.setTroca(troca);
				rastreio = (Rastreio) rastreioPersistence.salvar(rastreio);
				enviaEmailTrocaCodigoRastreio.processar(rastreio);
			}
		}

		return null;
	}
}
