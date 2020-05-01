package br.com.cascao.realmofcard.negocio.strategy.troca;

import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Pessoa;
import br.com.cascao.realmofcard.domain.Troca;
import br.com.cascao.realmofcard.negocio.strategy.IStrategy;
import br.com.cascao.realmofcard.util.validador.ValidadorString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RetiraQuantidadeItemDoPedido implements IStrategy{

	@Autowired
	ValidadorString validadorString;

	@Override
	public String processar(final EntidadeDominio entidade) {

		StringBuilder msg = new StringBuilder();

		if(entidade instanceof Troca){

			Troca troca = (Troca) entidade;

			troca.getItemListParaTroca().forEach( itemTroca -> {

				Integer quantidadeAtual = itemTroca.getItemParaTroca().getQuantidade();

				Integer quantidadeParaTrocar = itemTroca.getQuantidade();

				itemTroca.getItemParaTroca().setQuantidade(quantidadeAtual - quantidadeParaTrocar);
			});
		}

		return msg.toString();
	}
}
