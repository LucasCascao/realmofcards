package br.edu.les.realmofcard.strategy.cupom;

import br.edu.les.realmofcard.domain.Cupom;
import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.repository.CupomRepository;
import br.edu.les.realmofcard.strategy.IStrategy;
import br.edu.les.realmofcard.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaExistenciaCupom implements IStrategy {

	@Autowired
	private CupomRepository cupomRepository;

	@Override
	public String processar(final EntidadeDominio entidade) {
		StringBuilder msg = new StringBuilder();
		if(entidade instanceof Cupom){
			Cupom cupom = (Cupom) entidade;
			if(Util.isNotNull(cupom.getCodigo()) && cupom.getCodigo() != ""){
				if(Util.isNull(cupomRepository.findCupomByCodigo(cupom.getCodigo()))){
					msg.append("Cupom não existe.");
				}
			}
		}
		return msg.toString();
	}
}
