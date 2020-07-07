package br.edu.les.realmofcard.strategy.transacao_status_carta;

import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.TransacaoStatusCarta;
import br.edu.les.realmofcard.strategy.IStrategy;
import br.edu.les.realmofcard.util.validador.ValidadorString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaDadosTransacaoStatusCarta implements IStrategy {

    @Autowired
    ValidadorString validadorString;

    @Override
    public String processar(EntidadeDominio entidade) {

        StringBuilder msg = new StringBuilder();

        if(entidade instanceof TransacaoStatusCarta){
            TransacaoStatusCarta transacaoStatusCarta = (TransacaoStatusCarta) entidade;
            validadorString.validar(transacaoStatusCarta.getCarta(), "carta");
            validadorString.validar(transacaoStatusCarta.getMotivo(), "motivo");
        }
        return msg.toString();
    }
}
