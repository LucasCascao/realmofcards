package br.com.cascao.realmofcard.negocio.strategy.carta;

import br.com.cascao.realmofcard.domain.Carta;
import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.negocio.strategy.IStrategy;
import br.com.cascao.realmofcard.util.validador.DoubleValidador;
import br.com.cascao.realmofcard.util.validador.ValidadorString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
            msg.append(doubleValidador.validar(carta.getPrecificacao(), "valor de precificação"));
            msg.append(validadorString.validar(carta.getImagemPath(), "imagem"));
            msg.append(validadorString.validar(carta.getJogo().getId(), "jogo"));
            msg.append(validadorString.validar(carta.getCategoriaCarta().getId(), "categoria"));
        }
        return msg.toString();
    }
}
