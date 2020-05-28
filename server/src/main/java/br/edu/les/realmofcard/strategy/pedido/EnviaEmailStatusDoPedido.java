package br.edu.les.realmofcard.strategy.pedido;

import br.edu.les.realmofcard.dao.RastreioDAO;
import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.Pedido;
import br.edu.les.realmofcard.domain.Troca;
import br.edu.les.realmofcard.strategy.IStrategy;
import br.edu.les.realmofcard.strategy.email.pedido.*;
import br.edu.les.realmofcard.strategy.email.troca.EnviaEmailSolicitacaoTroca;
import br.edu.les.realmofcard.strategy.email.troca.EnviaEmailTrocaAprovadaComCupom;
import br.edu.les.realmofcard.strategy.email.troca.EnviaEmailTrocaRecusada;
import br.edu.les.realmofcard.strategy.troca.GeraCodigoRastreioTroca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EnviaEmailStatusDoPedido implements IStrategy {

	@Autowired
	private GeraCodigoRastreioPedido geraCodigoRastreioPedido;

	@Autowired
	private EnviaEmailPedidoEntregue enviaEmailPedidoEntregue;

	@Autowired
	private EnviaEmailPedidoPendentePagamento enviaEmailPedidoPendentePagamento;

	@Autowired
	private EnviaEmailPedidoRecusada enviaEmailPedidoRecusada;

	@Override
	public String processar(final EntidadeDominio entidade) {

		if(entidade instanceof Pedido){

			Pedido pedido = (Pedido) entidade;

			if(pedido.getStatusPedido() != null
				&& pedido.getStatusPedido().getId() != null){

				Map<Integer, IStrategy> mapaEnvioDeEmail = new HashMap<>();

				mapaEnvioDeEmail.put(1, enviaEmailPedidoPendentePagamento);
				mapaEnvioDeEmail.put(4, enviaEmailPedidoRecusada);
				mapaEnvioDeEmail.put(5, geraCodigoRastreioPedido);
				mapaEnvioDeEmail.put(6, enviaEmailPedidoEntregue);

				Integer statusPedidoId = pedido.getStatusPedido().getId();

				if(mapaEnvioDeEmail.containsKey(statusPedidoId)){
					mapaEnvioDeEmail.get(statusPedidoId).processar(pedido);
				}
			}
		}

		return null;
	}
}
