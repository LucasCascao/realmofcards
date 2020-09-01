package br.edu.les.realmofcard.strategy.troca;

import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.Transicao;
import br.edu.les.realmofcard.strategy.IStrategy;
import br.edu.les.realmofcard.strategy.email.troca.EnviaEmailSolicitacaoTroca;
import br.edu.les.realmofcard.strategy.email.troca.EnviaEmailTrocaCodigoRastreio;
import br.edu.les.realmofcard.strategy.email.troca.EnviaEmailTrocaRecusada;
import br.edu.les.realmofcard.strategy.transicao.GeraCodigoRastreioTransicao;
import br.edu.les.realmofcard.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EnviaEmailStatusDaTroca implements IStrategy {

	@Autowired
	private ValidaDadosTroca validaDadosTroca;

	@Autowired
	private EnviaEmailSolicitacaoTroca enviaEmailSolicitacaoTroca;

	@Autowired
	private EnviaEmailTrocaCodigoRastreio enviaEmailTrocaCodigoRastreio;

	@Autowired
	private EnviaEmailTrocaRecusada enviaEmailTrocaRecusada;

	@Override
	public String processar(final EntidadeDominio entidade) {

		if(entidade instanceof Transicao){

			Transicao transicao = (Transicao) entidade;

			if(transicao.getPedido() != null
				&& transicao.getPedido().getStatusPedido() != null
				&& transicao.getPedido().getStatusPedido().getId() != null
				&& Util.isNotNull(transicao.getTipoTransicao())
				&& transicao.getTipoTransicao().getId().equals(1)){

				Map<Integer, IStrategy> mapaEnvioDeEmail = new HashMap<>();

				mapaEnvioDeEmail.put(7, enviaEmailSolicitacaoTroca);
				mapaEnvioDeEmail.put(8, enviaEmailTrocaRecusada);
				mapaEnvioDeEmail.put(9, enviaEmailTrocaCodigoRastreio);

				Integer statusPedidoId = transicao.getPedido().getStatusPedido().getId();

				String msg = validaDadosTroca.processar(transicao);

				if(mapaEnvioDeEmail.containsKey(statusPedidoId)){
					mapaEnvioDeEmail.get(statusPedidoId).processar(transicao);
				}
			}
		}

		return null;
	}
}
