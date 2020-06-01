package br.edu.les.realmofcard.strategy.troca;

import br.edu.les.realmofcard.dao.RastreioDAO;
import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.Transicao;
import br.edu.les.realmofcard.strategy.IStrategy;
import br.edu.les.realmofcard.strategy.email.troca.EnviaEmailSolicitacaoTroca;
import br.edu.les.realmofcard.strategy.email.troca.EnviaEmailTrocaAprovadaComCupom;
import br.edu.les.realmofcard.strategy.email.troca.EnviaEmailTrocaRecusada;
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
	private GeraCodigoRastreioTroca geraCodigoRastreioTroca;

	@Autowired
	private EnviaEmailSolicitacaoTroca enviaEmailSolicitacaoTroca;

	@Autowired
	private EnviaEmailTrocaRecusada enviaEmailTrocaRecusada;

	@Override
	public String processar(final EntidadeDominio entidade) {

		if(entidade instanceof Transicao){

			Transicao transicao = (Transicao) entidade;

			if(transicao.getPedido() != null
				&& transicao.getPedido().getStatusPedido() != null
				&& transicao.getPedido().getStatusPedido().getId() != null){

				Map<Integer, IStrategy> mapaEnvioDeEmail = new HashMap<>();

				mapaEnvioDeEmail.put(7, enviaEmailSolicitacaoTroca);
				mapaEnvioDeEmail.put(8, enviaEmailTrocaRecusada);
				mapaEnvioDeEmail.put(9, geraCodigoRastreioTroca);

				Integer statusPedidoId = transicao.getPedido().getStatusPedido().getId();

				String msg = validaDadosTroca.processar(transicao);

				if(mapaEnvioDeEmail.containsKey(statusPedidoId) && msg.isEmpty()){
					mapaEnvioDeEmail.get(statusPedidoId).processar(transicao);
				}
			}
		}

		return null;
	}
}
