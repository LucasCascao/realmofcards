package br.edu.les.realmofcard.strategy.carta;

import br.edu.les.realmofcard.domain.Carta;
import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.strategy.IStrategy;
import br.edu.les.realmofcard.util.GeradorCodigo;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class InsereDataCadastro implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {
        if(entidade instanceof Carta){
            Carta carta = (Carta) entidade;
            carta.setDataCadastro(LocalDate.now());
        }
        return null;
    }
}
