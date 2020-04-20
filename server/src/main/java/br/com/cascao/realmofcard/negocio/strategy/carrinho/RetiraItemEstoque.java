package br.com.cascao.realmofcard.negocio.strategy.carrinho;

import br.com.cascao.realmofcard.domain.*;
import br.com.cascao.realmofcard.negocio.strategy.IStrategy;
import br.com.cascao.realmofcard.repository.ItemRepository;
import br.com.cascao.realmofcard.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class RetiraItemEstoque implements IStrategy {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public String processar(EntidadeDominio entidade) {

        if(entidade instanceof Carrinho){

            List<Item> itemList = ((Carrinho) entidade).getItemList();

            itemList.forEach( item -> {

                Integer diferencaDeQuantidadeItemCarrinho = null;

                if(item.getId() != null){
                    Item itemNoCarrinho = itemRepository.findById(item.getId()).get();
                    if(Util.isNotNull(itemNoCarrinho))
                        diferencaDeQuantidadeItemCarrinho = item.getQuantidade() - itemNoCarrinho.getQuantidade();
                    else
                        diferencaDeQuantidadeItemCarrinho = item.getQuantidade();
                } else
                    diferencaDeQuantidadeItemCarrinho = item.getQuantidade();

                if(item.getQuantidade() <= item.getCarta().getQuantidadeDisponivel()){
                    item.getCarta().setQuantidadeDisponivel(item.getCarta().getQuantidadeDisponivel() - diferencaDeQuantidadeItemCarrinho);
                    if(item.getCarta().getQuantidadeDisponivel() == 0){
                        item.getCarta().setStatus(Status.builder().id(2).build());
                    }
                }
            });
        }

        return null;
    }
}
