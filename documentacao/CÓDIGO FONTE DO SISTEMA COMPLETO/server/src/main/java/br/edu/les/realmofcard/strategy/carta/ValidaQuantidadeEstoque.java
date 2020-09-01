package br.edu.les.realmofcard.strategy.carta;

import br.edu.les.realmofcard.dao.CarrinhoDAO;
import br.edu.les.realmofcard.dao.CartaDAO;
import br.edu.les.realmofcard.domain.Carrinho;
import br.edu.les.realmofcard.domain.Carta;
import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.repository.CarrinhoRepository;
import br.edu.les.realmofcard.strategy.IStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidaQuantidadeEstoque implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {

        StringBuilder msg = new StringBuilder();

        if(entidade instanceof Carta){
            Carta carta = (Carta) entidade;
            if(carta.getQuantidadeEstoque() < 0){
                msg.append("Valor não pode ser maior que o está em estoque atualmente");
            }
        }

        return msg.toString();
    }
}
