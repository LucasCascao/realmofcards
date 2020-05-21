package br.edu.les.realmofcard.strategy.troca;

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

			if(Util.isNull(troca.getItemListParaTroca())) msg.append("Campo de item é obrigatório.");
			if(Util.isNull(troca.getPedidoParaTroca())) msg.append("Pedido é obrigatório.");
		}

		return msg.toString();
	}
}
