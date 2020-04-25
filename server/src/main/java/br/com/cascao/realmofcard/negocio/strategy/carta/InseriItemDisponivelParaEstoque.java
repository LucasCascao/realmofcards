package br.com.cascao.realmofcard.negocio.strategy.carta;

import br.com.cascao.realmofcard.domain.Carta;
import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.negocio.strategy.IStrategy;
import br.com.cascao.realmofcard.util.Util;
import org.springframework.stereotype.Component;

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
