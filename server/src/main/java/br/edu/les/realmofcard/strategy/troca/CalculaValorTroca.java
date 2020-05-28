package br.edu.les.realmofcard.strategy.troca;

import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.Item;
import br.edu.les.realmofcard.domain.ItemTroca;
import br.edu.les.realmofcard.domain.Troca;
import br.edu.les.realmofcard.repository.ItemRepository;
import br.edu.les.realmofcard.strategy.IStrategy;
import br.edu.les.realmofcard.util.Util;
import br.edu.les.realmofcard.util.validador.ValidadorString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CalculaValorTroca implements IStrategy {

	@Autowired
	private ItemRepository itemRepository;

	@Override
	public String processar(final EntidadeDominio entidade) {
		StringBuilder msg = new StringBuilder();
		if(entidade instanceof Troca){
			Troca troca = (Troca) entidade;
			if(Util.isNotNull(troca.getItemListParaTroca())){
				Double subTotal = 0.0;
				for (ItemTroca itemTroca : troca.getItemListParaTroca()) {
					if(Util.isNotNull(itemTroca.getItemParaTroca().getId())){
						Item item = itemRepository.findById(itemTroca.getItemParaTroca().getId()).get();
						subTotal += item.getCarta().getValorVenda() * itemTroca.getQuantidade();
					}
				}
				troca.setSubTotal(subTotal);
			}
		}
		return msg.toString();
	}
}
