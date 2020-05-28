package br.edu.les.realmofcard.strategy.carta;

import br.edu.les.realmofcard.strategy.IStrategy;
import org.springframework.stereotype.Component;

import br.edu.les.realmofcard.domain.Carta;
import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.util.Util;

@Component
public class InseriItemDisponivelParaEstoque implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {

        StringBuilder msg = new StringBuilder();

        if(entidade instanceof Carta){
            Carta carta = (Carta) entidade;
            if(Util.isNotNull(carta) && Util.isNotNull(carta.getQuantidadeDisponivel())){
                carta.setQuantidadeEstoque(carta.getQuantidadeDisponivel());
            }
        }

        return null;
    }
}
