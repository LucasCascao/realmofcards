package br.com.cascao.realmofcard.negocio.strategy.pedido;

import br.com.cascao.realmofcard.domain.*;
import br.com.cascao.realmofcard.negocio.strategy.IStrategy;
import br.com.cascao.realmofcard.repository.CarrinhoRepository;
import br.com.cascao.realmofcard.repository.ItemRepository;
import br.com.cascao.realmofcard.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RetiraItemEstoque implements IStrategy {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Override
    public String processar(EntidadeDominio entidade) {

        if(entidade instanceof Pedido){

            List<Item> itemList = ((Pedido) entidade).getItemList();

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
