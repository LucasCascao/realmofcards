package br.edu.les.realmofcard.strategy.troca;

import br.edu.les.realmofcard.domain.ItemTroca;
import br.edu.les.realmofcard.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.Troca;
import br.edu.les.realmofcard.util.Util;
import br.edu.les.realmofcard.util.validador.ValidadorString;

@Component
public class ValidaDadosTroca implements IStrategy {

	@Autowired
	ValidadorString validadorString;

	@Override
	public String processar(final EntidadeDominio entidade) {

		StringBuilder msg = new StringBuilder();

		if(entidade instanceof Troca){

			Troca troca = (Troca) entidade;

			if(Util.isNull(troca.getItemListParaTroca())) {
				msg.append("Lista de itens para troca não foi enviada.");
			} else {
				if(troca.getItemListParaTroca().size() < 1){
					msg.append("Campo de item é obrigatório.");
				} else {
					for (ItemTroca itemTroca : troca.getItemListParaTroca()) {
						if(itemTroca.getQuantidade() > itemTroca.getItemParaTroca().getQuantidade()){
							msg.append("A quantidade de troca da carta " + itemTroca.getItemParaTroca().getCarta().getNome()
									+ " deve ser menor que ou igual a " + itemTroca.getItemParaTroca().getQuantidade() + ".");
						} else if(itemTroca.getQuantidade() < 1 ){
							msg.append("A quantidade de troca da carta " + itemTroca.getItemParaTroca().getCarta().getNome()
									+ " deve ser maior que 0.");
						}
					}
				}
			}
			if(Util.isNull(troca.getPedidoParaTroca())) msg.append("Pedido é obrigatório.");
		}

		return msg.toString();
	}
}
