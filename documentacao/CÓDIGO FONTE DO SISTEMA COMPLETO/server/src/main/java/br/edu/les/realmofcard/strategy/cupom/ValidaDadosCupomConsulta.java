package br.edu.les.realmofcard.strategy.cupom;

import br.edu.les.realmofcard.domain.Cupom;
import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.repository.CupomRepository;
import br.edu.les.realmofcard.strategy.IStrategy;
import br.edu.les.realmofcard.util.Util;
import br.edu.les.realmofcard.util.validador.ValidadorString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaDadosCupomConsulta implements IStrategy {

	@Autowired
	private ValidadorString validadorString;

	@Override
	public String processar(final EntidadeDominio entidade) {
		StringBuilder msg = new StringBuilder();
		if(entidade instanceof Cupom){
			Cupom cupom = (Cupom) entidade;
			if(Util.isNotNull(cupom.getStatus())){
				msg.append(validadorString.validar(cupom.getCodigo(), "código do cupom"));
			}
		}
		return msg.toString();
	}
}
