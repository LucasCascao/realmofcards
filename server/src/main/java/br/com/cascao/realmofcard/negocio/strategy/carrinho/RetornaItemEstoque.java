package br.com.cascao.realmofcard.negocio.strategy.carrinho;

import br.com.cascao.realmofcard.domain.Carrinho;
import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Item;
import br.com.cascao.realmofcard.negocio.strategy.IStrategy;
import br.com.cascao.realmofcard.repository.CarrinhoRepository;
import br.com.cascao.realmofcard.repository.ItemRepository;
import br.com.cascao.realmofcard.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RetornaItemEstoque implements IStrategy {

    @Autowired
    ItemRepository itemRepository;

    @Override
    public String processar(EntidadeDominio entidade) {

        if(entidade instanceof Item){

            Item item = (Item) entidade;

            item = itemRepository.findById(item.getId()).get();

            if(Util.isNotNull(item)) {

                item.getCarta().setQuantidadeDisponivel(item.getCarta().getQuantidadeEstoque());

            }

        }

        return null;
    }
}
