package br.edu.les.realmofcard.strategy.grupo_precificacao;

import br.edu.les.realmofcard.domain.Endereco;
import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.GrupoPrecificacao;
import br.edu.les.realmofcard.strategy.IStrategy;
import br.edu.les.realmofcard.util.Util;
import br.edu.les.realmofcard.util.validador.ValidadorString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaDadosGrupoPrecificacao implements IStrategy {

    @Autowired
    ValidadorString validadorString;

    @Override
    public String processar(EntidadeDominio entidade) {

        StringBuilder msg = new StringBuilder();

        if(entidade instanceof GrupoPrecificacao){
            GrupoPrecificacao grupoPrecificacao = (GrupoPrecificacao) entidade;
            if (Util.isNotNull(grupoPrecificacao.getValor())) {
                msg.append("Campo valor da precificação");
            }
        }
        return msg.toString();
    }
}
