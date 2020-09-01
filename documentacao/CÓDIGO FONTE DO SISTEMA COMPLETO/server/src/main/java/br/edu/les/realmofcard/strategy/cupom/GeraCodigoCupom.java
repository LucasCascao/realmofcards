package br.edu.les.realmofcard.strategy.cupom;

import br.edu.les.realmofcard.strategy.IStrategy;
import org.springframework.stereotype.Component;

import br.edu.les.realmofcard.domain.*;
import br.edu.les.realmofcard.util.GeradorCodigo;

import java.time.LocalDate;

@Component
public class GeraCodigoCupom implements IStrategy {

	@Override
	public String processar(final EntidadeDominio entidade) {

		if(entidade instanceof Cupom){

			Cupom cupom = (Cupom) entidade;

			cupom.setCodigo(GeradorCodigo.gerarCodigoCupom());
			cupom.setStatus(Status.builder().id(1).build());
			cupom.setDataCriacao(LocalDate.now());
		}
		return null;
	}
}
