package br.com.cascao.realmofcard.negocio.strategy.carta;

import br.com.cascao.realmofcard.domain.Carta;
import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.negocio.strategy.IStrategy;
import br.com.cascao.realmofcard.repository.CartaRepository;
import br.com.cascao.realmofcard.util.MovedorImagem;
import br.com.cascao.realmofcard.util.validador.ValidadorString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
            if(carta.getImagemPath()!= null
                    && cartaRepository.existsByImagemPath(carta.getImagemPath())
                    && MovedorImagem.existeImagem(carta.getImagemPath())){
                try{
                    carta.setImagemPath(MovedorImagem.mover(carta.getImagemPath()));
                }catch (IOException e){
                    msg.append("Erro ao salvar imagem.");
                    e.printStackTrace();
                }
            }
        }
        return msg.toString();
    }
}
