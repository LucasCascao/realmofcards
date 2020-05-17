package br.edu.les.realmofcard.strategy.troca;

import br.edu.les.realmofcard.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.Item;
import br.edu.les.realmofcard.domain.Pedido;
import br.edu.les.realmofcard.domain.Troca;
import br.edu.les.realmofcard.util.Util;
import br.edu.les.realmofcard.util.validador.ValidadorString;

@Component
public class RetiraQuantidadeItemDoPedido implements IStrategy {

	@Autowired
	ValidadorString validadorString;

	@Override
	public String processar(final EntidadeDominio entidade) {

		StringBuilder msg = new StringBuilder();

		if(entidade instanceof Troca){

			Troca troca = (Troca) entidade;
			
			Pedido pedido = troca.getPedidoParaTroca();
			
			if(Util.isNotNull(pedido)
				&& Util.isNotNull(pedido.getStatusPedido())
				&& Util.isNotNull(pedido.getStatusPedido().getId())
				&& pedido.getStatusPedido().getId().equals(7)) {
				
				troca.getItemListParaTroca().forEach( itemTroca -> {
				
					Item item = itemTroca.getItemParaTroca();
	
					Integer quantidadeAtual = item.getQuantidade();
	
					Integer quantidadeParaTrocar = itemTroca.getQuantidade();
	
					item.setQuantidade(quantidadeAtual - quantidadeParaTrocar);
					
					item.setQuantidadeTroca(item.getQuantidadeTroca() + quantidadeParaTrocar);
				});
			}

			
		}

		return msg.toString();
	}
}
