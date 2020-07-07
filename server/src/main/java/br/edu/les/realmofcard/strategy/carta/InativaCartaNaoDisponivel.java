package br.edu.les.realmofcard.strategy.carta;

import br.edu.les.realmofcard.dao.CartaDAO;
import br.edu.les.realmofcard.domain.Carrinho;
import br.edu.les.realmofcard.domain.Carta;
import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.Status;
import br.edu.les.realmofcard.repository.CarrinhoRepository;
import br.edu.les.realmofcard.strategy.IStrategy;
import br.edu.les.realmofcard.util.GeradorCodigo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InativaCartaNaoDisponivel implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {
        if(entidade instanceof Carta){
            Carta carta = (Carta) entidade;
            if(carta.getQuantidadeDisponivel() <= 0){
                carta.setStatus(Status.builder().id(2).build());
            }
        }
        return null;
    }
}
