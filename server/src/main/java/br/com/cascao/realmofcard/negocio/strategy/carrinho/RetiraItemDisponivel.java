package br.com.cascao.realmofcard.negocio.strategy.carrinho;

import br.com.cascao.realmofcard.domain.*;
import br.com.cascao.realmofcard.negocio.strategy.IStrategy;
import br.com.cascao.realmofcard.repository.CarrinhoRepository;
import br.com.cascao.realmofcard.repository.ItemRepository;
import br.com.cascao.realmofcard.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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
