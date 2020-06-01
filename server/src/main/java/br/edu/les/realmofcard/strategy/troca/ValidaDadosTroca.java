package br.edu.les.realmofcard.strategy.troca;

import br.edu.les.realmofcard.domain.ItemTransacao;
import br.edu.les.realmofcard.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.Transicao;
import br.edu.les.realmofcard.util.Util;
import br.edu.les.realmofcard.util.validador.ValidadorString;

@Component
public class ValidaDadosTroca implements IStrategy {

	@Autowired
	ValidadorString validadorString;

	@Override
	public String processar(final EntidadeDominio entidade) {

		StringBuilder msg = new StringBuilder();

		if(entidade instanceof Transicao){

			Transicao transicao = (Transicao) entidade;

			if(Util.isNotNull(transicao.getTipoTransicao())
				&& Util.isNotNull(transicao.getTipoTransicao().getId())
				&& transicao.getTipoTransicao().getId().equals(1)){

				if(Util.isNull(transicao.getItemTransicaoList())) {
					msg.append("Lista de itens para troca não foi enviada.");
				} else {
					if(transicao.getItemTransicaoList().size() < 1){
						msg.append("Campo de item é obrigatório.");
					} else {
						for (ItemTransacao itemTransacao : transicao.getItemTransicaoList()) {
							if(itemTransacao.getQuantidade() > itemTransacao.getItem().getQuantidade()){
								msg.append("A quantidade de troca da carta " + itemTransacao.getItem().getCarta().getNome()
										+ " deve ser menor que ou igual a " + itemTransacao.getItem().getQuantidade() + ".");
							} else if(itemTransacao.getQuantidade() < 1 ){
								msg.append("A quantidade de troca da carta " + itemTransacao.getItem().getCarta().getNome()
										+ " deve ser maior que 0.");
							}
						}
					}
				}
				if(Util.isNull(transicao.getPedido())) msg.append("Pedido é obrigatório.");
			}
		}
		return msg.toString();
	}
}
