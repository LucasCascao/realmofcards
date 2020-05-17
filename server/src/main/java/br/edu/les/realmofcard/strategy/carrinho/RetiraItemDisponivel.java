package br.edu.les.realmofcard.strategy.carrinho;

import br.edu.les.realmofcard.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.les.realmofcard.domain.*;
import br.edu.les.realmofcard.repository.CarrinhoRepository;
import br.edu.les.realmofcard.repository.ItemRepository;
import br.edu.les.realmofcard.util.Util;

import java.util.List;

@Component
public class RetiraItemDisponivel implements IStrategy {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Override
    public String processar(EntidadeDominio entidade) {

        if(entidade instanceof Carrinho){

            List<Item> itemList = ((Carrinho) entidade).getItemList();

            for(Item item : itemList){

                Integer diferencaDeQuantidadeItemCarrinho;
                Integer quantidadeItemDisponivel;

                if(item.getId() != null){
                    Item itemNoCarrinho = itemRepository.findById(item.getId()).get();
                    if(Util.isNotNull(itemNoCarrinho)){
                        diferencaDeQuantidadeItemCarrinho = item.getQuantidade() - itemNoCarrinho.getQuantidade();
                        quantidadeItemDisponivel = itemNoCarrinho.getCarta().getQuantidadeDisponivel();
                    } else {
                        diferencaDeQuantidadeItemCarrinho = item.getQuantidade();
                        quantidadeItemDisponivel = item.getCarta().getQuantidadeDisponivel();
                    }
                } else {
                    diferencaDeQuantidadeItemCarrinho = item.getQuantidade();
                    quantidadeItemDisponivel = item.getCarta().getQuantidadeDisponivel();
                }


                item.getCarta().setQuantidadeDisponivel(quantidadeItemDisponivel - diferencaDeQuantidadeItemCarrinho);

                if(item.getCarta().getQuantidadeDisponivel() == 0){
                    item.getCarta().setStatus(Status.builder().id(2).build());
                } else {
                    item.getCarta().setStatus(Status.builder().id(1).build());
                }
            }
        }

        return null;
    }
}
