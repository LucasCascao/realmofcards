package br.edu.les.realmofcard.strategy.devolucao;

import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.Transicao;
import br.edu.les.realmofcard.strategy.IStrategy;
import br.edu.les.realmofcard.strategy.email.devolucao.EnviaEmailDevolucaoAprovadaComCupom;
import br.edu.les.realmofcard.strategy.email.devolucao.EnviaEmailDevolucaoCodigoRastreio;
import br.edu.les.realmofcard.strategy.email.devolucao.EnviaEmailDevolucaoRecusada;
import br.edu.les.realmofcard.strategy.email.devolucao.EnviaEmailSolicitacaoDevolucao;
import br.edu.les.realmofcard.strategy.email.troca.EnviaEmailSolicitacaoTroca;
import br.edu.les.realmofcard.strategy.email.troca.EnviaEmailTrocaRecusada;
import br.edu.les.realmofcard.strategy.transicao.GeraCodigoRastreioTransicao;
import br.edu.les.realmofcard.strategy.troca.ValidaDadosTroca;
import br.edu.les.realmofcard.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EnviaEmailStatusDaDevolucao implements IStrategy {

	@Autowired
	private EnviaEmailSolicitacaoDevolucao enviaEmailSolicitacaoDevolucao;

	@Autowired
	private EnviaEmailDevolucaoRecusada enviaEmailDevolucaoRecusada;

	@Autowired
	private EnviaEmailDevolucaoCodigoRastreio enviaEmailDevolucaoCodigoRastreio;

	@Override
	public String processar(final EntidadeDominio entidade) {

		if(entidade instanceof Transicao){

			Transicao transicao = (Transicao) entidade;

			if(transicao.getPedido() != null
				&& transicao.getPedido().getStatusPedido() != null
				&& transicao.getPedido().getStatusPedido().getId() != null
				&& Util.isNotNull(transicao.getTipoTransicao())
				&& transicao.getTipoTransicao().getId().equals(2)){

				Map<Integer, IStrategy> mapaEnvioDeEmail = new HashMap<>();

				mapaEnvioDeEmail.put(11, enviaEmailSolicitacaoDevolucao);
				mapaEnvioDeEmail.put(12, enviaEmailDevolucaoRecusada);
				mapaEnvioDeEmail.put(9, enviaEmailDevolucaoCodigoRastreio);

				Integer statusPedidoId = transicao.getPedido().getStatusPedido().getId();

				if(mapaEnvioDeEmail.containsKey(statusPedidoId)){
					mapaEnvioDeEmail.get(statusPedidoId).processar(transicao);
				}
			}
		}

		return null;
	}
}
