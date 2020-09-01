package br.edu.les.realmofcard.strategy.carrinho;

import br.edu.les.realmofcard.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.les.realmofcard.domain.Carrinho;
import br.edu.les.realmofcard.domain.Carta;
import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.Item;
import br.edu.les.realmofcard.repository.CarrinhoRepository;
import br.edu.les.realmofcard.repository.CartaRepository;
import br.edu.les.realmofcard.util.Util;

import java.util.List;

@Component
public class ValidaQuantidadeItemDisponivel implements IStrategy {

    @Autowired
    CarrinhoRepository carrinhoRepository;

    @Autowired
    CartaRepository cartaRepository;

    @Override
    public String processar(EntidadeDominio entidade) {

        StringBuilder msg = new StringBuilder();

        if(entidade instanceof Carrinho || entidade instanceof Item){

            if(entidade instanceof Carrinho){
                List<Item> itemList = ((Carrinho) entidade).getItemList();

                if(Util.isNotNull(itemList)){

                    itemList.forEach( item -> {

                        Carta carta = cartaRepository.findById(item.getCarta().getId()).get();

                        if(item.getQuantidade() > carta.getQuantidadeEstoque())
                            msg.append("Quantidade pedido não está disponível no estoque.");

                        if(item.getQuantidade() < 1)
                            msg.append("Quantidade pedido não é válida para incluir no carrinho.");

                    });
                }
            }

            if(entidade instanceof Item) {

                Item item = (Item) entidade;

                Carta carta = cartaRepository.findById(item.getCarta().getId()).get();

                if(item.getQuantidade() > carta.getQuantidadeEstoque())
                    msg.append("Quantidade pedido não está disponível no estoque.");
            }

        }
        return msg.toString();
    }
}
