package br.edu.les.realmofcard.strategy.transacao_status_carta;

import br.edu.les.realmofcard.domain.Carta;
import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.Status;
import br.edu.les.realmofcard.domain.TransacaoStatusCarta;
import br.edu.les.realmofcard.strategy.IStrategy;
import br.edu.les.realmofcard.util.Util;
import br.edu.les.realmofcard.util.validador.ValidadorString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AltararStatusCarta implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {

        if(entidade instanceof TransacaoStatusCarta){
            TransacaoStatusCarta transacaoStatusCarta = (TransacaoStatusCarta) entidade;
            if(Util.isNotNull(transacaoStatusCarta.getCarta())){
                Carta carta = transacaoStatusCarta.getCarta();
                Status status;
                if(carta.getStatus().getId() == 1){
                    status = Status.builder().id(2).build();
                } else {
                    status = Status.builder().id(1).build();
                }
                carta.setStatus(status);
                transacaoStatusCarta.setStatus(status);
            }
        }

        return null;
    }
}
