package br.edu.les.realmofcard.strategy.carta;

import br.edu.les.realmofcard.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.les.realmofcard.domain.Carta;
import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.util.validador.DoubleValidador;
import br.edu.les.realmofcard.util.validador.ValidadorString;

@Component
public class ValidaDadosCarta implements IStrategy {

    @Autowired
    ValidadorString validadorString;

    @Autowired
    DoubleValidador doubleValidador;

    @Override
    public String processar(EntidadeDominio entidade) {
        StringBuilder msg = new StringBuilder();
        if (entidade instanceof Carta){
            Carta carta = (Carta) entidade;
            msg.append(validadorString.validar(carta.getNome(), "nome"));
            msg.append(validadorString.validar(carta.getDescricao(), "descrição"));
            msg.append(doubleValidador.validar(carta.getValorCompra(), "valor do produto"));
            msg.append(doubleValidador.validar(carta.getGrupoPrecificacao(), "valor de precificação"));
            msg.append(validadorString.validar(carta.getImagemPath(), "imagem"));
            msg.append(validadorString.validar(carta.getJogo().getId(), "jogo"));
            msg.append(validadorString.validar(carta.getCategoriaCarta().getId(), "categoria"));
        }
        return msg.toString();
    }
}
