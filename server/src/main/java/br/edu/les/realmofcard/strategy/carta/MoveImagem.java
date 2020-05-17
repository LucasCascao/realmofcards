package br.edu.les.realmofcard.strategy.carta;

import br.edu.les.realmofcard.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.les.realmofcard.domain.Carta;
import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.repository.CartaRepository;
import br.edu.les.realmofcard.util.validador.ValidadorString;

@Component
public class MoveImagem implements IStrategy {

    @Autowired
    ValidadorString validadorString;

    @Autowired
    CartaRepository cartaRepository;

    @Override
    public String processar(EntidadeDominio entidade) {

        StringBuilder msg = new StringBuilder();

        if (entidade instanceof Carta){

            Carta carta = (Carta) entidade;

            if(carta.getImagemPath() != null
                && !carta.getImagemPath().contains("/assets/images/cartas/")){

                carta.setImagemPath("/assets/images/cartas/" + carta.getImagemPath());
            }
        }
        return msg.toString();
    }
}
