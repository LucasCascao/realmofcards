package br.edu.les.realmofcard.strategy.troca;

import br.edu.les.realmofcard.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.Item;
import br.edu.les.realmofcard.domain.Pedido;
import br.edu.les.realmofcard.domain.Transicao;
import br.edu.les.realmofcard.util.Util;
import br.edu.les.realmofcard.util.validador.ValidadorString;

@Component
public class RetiraQuantidadeItemDoPedido implements IStrategy {

	@Autowired
	ValidadorString validadorString;

	@Override
	public String processar(final EntidadeDominio entidade) {

		StringBuilder msg = new StringBuilder();

		if(entidade instanceof Transicao){

			Transicao transicao = (Transicao) entidade;
			
			Pedido pedido = transicao.getPedido();
			
			if(Util.isNotNull(pedido)
				&& Util.isNotNull(pedido.getStatusPedido())
				&& Util.isNotNull(pedido.getStatusPedido().getId())
				&& pedido.getStatusPedido().getId().equals(7)) {
				
				transicao.getItemTransicaoList().forEach(itemTransacao -> {
				
					Item item = itemTransacao.getItem();
	
					Integer quantidadeAtual = item.getQuantidade();
	
					Integer quantidadeParaTrocar = itemTransacao.getQuantidade();
	
					item.setQuantidade(quantidadeAtual - quantidadeParaTrocar);
					
					item.setQuantidadeTroca(item.getQuantidadeTroca() + quantidadeParaTrocar);
				});
			}

			
		}

		return msg.toString();
	}
}
