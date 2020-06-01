package br.edu.les.realmofcard.strategy.transicao;

import br.edu.les.realmofcard.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.les.realmofcard.domain.*;
import br.edu.les.realmofcard.util.Util;
import br.edu.les.realmofcard.util.validador.ValidadorString;

@Component
public class RetornaQuantidadeItemPedidoParaEstoque implements IStrategy {

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
				&& (pedido.getStatusPedido().getId().equals(10) || pedido.getStatusPedido().getId().equals(13))) {
				
				transicao.getItemTransicaoList().forEach(itemTransacao -> {
				
					Item item = itemTransacao.getItem();

					Carta carta = item.getCarta();

					carta.setQuantidadeDisponivel(carta.getQuantidadeDisponivel() + itemTransacao.getQuantidade());
					carta.setQuantidadeEstoque(carta.getQuantidadeEstoque() + itemTransacao.getQuantidade());

					if(carta.getStatus().getId().equals(2)){
						carta.setStatus(Status.builder().id(1).build());
					}
				});
			}
		}

		return msg.toString();
	}
}
