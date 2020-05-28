package br.edu.les.realmofcard.strategy.troca;

import br.edu.les.realmofcard.dao.RastreioDAO;
import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.Status;
import br.edu.les.realmofcard.domain.StatusPedido;
import br.edu.les.realmofcard.domain.Troca;
import br.edu.les.realmofcard.strategy.IStrategy;
import br.edu.les.realmofcard.strategy.email.troca.EnviaEmailSolicitacaoTroca;
import br.edu.les.realmofcard.strategy.email.troca.EnviaEmailTrocaAprovadaComCupom;
import br.edu.les.realmofcard.strategy.email.troca.EnviaEmailTrocaCodigoRastreio;
import br.edu.les.realmofcard.strategy.email.troca.EnviaEmailTrocaRecusada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EnviaEmailStatusDaTroca implements IStrategy {

	@Autowired
	private RastreioDAO rastreioDAO;

	@Autowired
	private GeraCodigoRastreioTroca geraCodigoRastreioTroca;

	@Autowired
	private EnviaEmailSolicitacaoTroca enviaEmailSolicitacaoTroca;

	@Autowired
	private EnviaEmailTrocaAprovadaComCupom enviaEmailTrocaAprovadaComCupom;

	@Autowired
	private EnviaEmailTrocaRecusada enviaEmailTrocaRecusada;

	@Override
	public String processar(final EntidadeDominio entidade) {

		if(entidade instanceof Troca){

			Troca troca = (Troca) entidade;

			if(troca.getPedidoParaTroca() != null
				&& troca.getPedidoParaTroca().getStatusPedido() != null
				&& troca.getPedidoParaTroca().getStatusPedido().getId() != null){

				Map<Integer, IStrategy> mapaEnvioDeEmail = new HashMap<>();

				mapaEnvioDeEmail.put(7, enviaEmailSolicitacaoTroca);
				mapaEnvioDeEmail.put(8, enviaEmailTrocaRecusada);
				mapaEnvioDeEmail.put(9, geraCodigoRastreioTroca);

				Integer statusPedidoId = troca.getPedidoParaTroca().getStatusPedido().getId();

				if(mapaEnvioDeEmail.containsKey(statusPedidoId)){
					mapaEnvioDeEmail.get(statusPedidoId).processar(troca);
				}
			}
		}

		return null;
	}
}
