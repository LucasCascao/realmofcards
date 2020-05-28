package br.edu.les.realmofcard.strategy.cupom;

import br.edu.les.realmofcard.domain.Cupom;
import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.repository.CupomRepository;
import br.edu.les.realmofcard.strategy.IStrategy;
import br.edu.les.realmofcard.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaCupomAtivo implements IStrategy {

    @Autowired
    private CupomRepository cupomRepository;

    @Override
    public String processar(EntidadeDominio entidade) {
        StringBuilder msg = new StringBuilder();
        if(entidade instanceof Cupom){
            Cupom cupom = (Cupom) entidade;
            if(Util.isNotNull(cupom.getCodigo())){
                cupom = cupomRepository.findCupomByCodigo(((Cupom) entidade).getCodigo());
                if(Util.isNotNull(cupom) && cupom.getStatus().getId() == 2){
                    msg.append("Cupom inativo.");
                }
            }
        }
        return msg.toString();
    }
}
