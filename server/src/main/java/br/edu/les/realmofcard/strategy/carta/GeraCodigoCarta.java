package br.edu.les.realmofcard.strategy.carta;

import br.edu.les.realmofcard.domain.Carta;
import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.strategy.IStrategy;
import br.edu.les.realmofcard.util.GeradorCodigo;
import br.edu.les.realmofcard.util.Util;
import org.springframework.stereotype.Component;

@Component
public class GeraCodigoCarta implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {
        if(entidade instanceof Carta){
            Carta carta = (Carta) entidade;
            carta.setCodigo(GeradorCodigo.gerarCodigoCarta());
        }
        return null;
    }
}
