package br.com.cascao.realmofcard.negocio.strategy.carta;

import br.com.cascao.realmofcard.domain.Carta;
import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.negocio.strategy.IStrategy;
import br.com.cascao.realmofcard.repository.CartaRepository;
import br.com.cascao.realmofcard.util.MovedorImagem;
import br.com.cascao.realmofcard.util.validador.ValidadorString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

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
            if(carta.getImagemPath() != null){
                carta.setImagemPath("/assets/images/cartas/" + carta.getImagemPath());
            }
        }
        return msg.toString();
    }
}
