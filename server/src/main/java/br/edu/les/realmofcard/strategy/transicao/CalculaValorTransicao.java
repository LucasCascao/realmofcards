package br.edu.les.realmofcard.strategy.transicao;

import br.edu.les.realmofcard.domain.*;
import br.edu.les.realmofcard.repository.ItemRepository;
import br.edu.les.realmofcard.strategy.IStrategy;
import br.edu.les.realmofcard.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CalculaValorTransicao implements IStrategy {

	@Autowired
	private ItemRepository itemRepository;

	@Override
	public String processar(final EntidadeDominio entidade) {
		if(entidade instanceof Transicao){
			Transicao transicao = (Transicao) entidade;
			if(Util.isNotNull(transicao.getItemTransicaoList())){
				Double subTotal = 0.0;
				for (ItemTransacao itemTransacao : transicao.getItemTransicaoList()) {
					if(Util.isNotNull(itemTransacao.getItem().getId())){
						Item item = itemRepository.findById(itemTransacao.getItem().getId()).get();
						Carta carta = item.getCarta();
						Double precificacao = item.getCarta().getGrupoPrecificacao().getValor();
						subTotal += (carta.getValorCompra() + (carta.getValorCompra() * precificacao)) * itemTransacao.getQuantidade();
					}
				}
				transicao.setSubTotal(subTotal);
			}
		}
		return null;
	}
}
