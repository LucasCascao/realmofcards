package br.edu.les.realmofcard.strategy.pedido;

import br.edu.les.realmofcard.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.les.realmofcard.domain.*;
import br.edu.les.realmofcard.repository.CarrinhoRepository;
import br.edu.les.realmofcard.repository.ItemRepository;
import br.edu.les.realmofcard.util.Util;

import java.util.Set;

@Component
public class RetiraItemEstoque implements IStrategy {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Override
    public String processar(EntidadeDominio entidade) {

        if(entidade instanceof Pedido){

            Set<Item> itemList = ((Pedido) entidade).getItemList();

            for(Item item : itemList){

                if(item.getId() != null){
                    Item itemNoCarrinho = itemRepository.findById(item.getId()).get();
                    if(Util.isNotNull(itemNoCarrinho)){
                        item.getCarta().setQuantidadeEstoque(item.getCarta().getQuantidadeEstoque() - item.getQuantidade());
                    }
                }
            }
        }

        return null;
    }
}
