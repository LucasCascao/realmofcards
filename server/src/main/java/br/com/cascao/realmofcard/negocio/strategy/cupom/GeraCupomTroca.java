package br.com.cascao.realmofcard.negocio.strategy.cupom;

import br.com.cascao.realmofcard.domain.*;
import br.com.cascao.realmofcard.negocio.strategy.IStrategy;
import br.com.cascao.realmofcard.persistence.CupomPersistence;
import br.com.cascao.realmofcard.repository.TrocaRepository;
import br.com.cascao.realmofcard.util.GeradorCodigo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GeraCupomTroca implements IStrategy{

	@Override
	public String processar(final EntidadeDominio entidade) {

		StringBuilder msg = new StringBuilder();

		if(entidade instanceof Cupom){

			Cupom cupom = (Cupom) entidade;

			Double valorTotal = 0.0;

			for (ItemTroca itemTroca : cupom.getTroca().getItemListParaTroca()) {
				valorTotal += itemTroca.getItemParaTroca().getCarta().getValorCompra() * itemTroca.getQuantidade();
			}

			cupom.setValor(valorTotal);
			cupom.setCodigo(GeradorCodigo.gerarCodigoCupom());
			cupom.setStatus(Status.builder().id(1).build());

		}

		return msg.toString();
	}
}
