package br.com.cascao.realmofcard.negocio.strategy.troca;

import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Troca;
import br.com.cascao.realmofcard.negocio.strategy.IStrategy;
import br.com.cascao.realmofcard.util.Util;
import br.com.cascao.realmofcard.util.validador.ValidadorString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaDadosTroca implements IStrategy{

	@Autowired
	ValidadorString validadorString;

	@Override
	public String processar(final EntidadeDominio entidade) {

		StringBuilder msg = new StringBuilder();

		if(entidade instanceof Troca){

			Troca troca = (Troca) entidade;

			if(Util.isNull(troca.getItemListParaTroca())) msg.append("Campo de item é obrigatário.");
			if(Util.isNull(troca.getPedidoParaTroca())) msg.append("Pedido é obrigatário.");
		}

		return msg.toString();
	}
}
