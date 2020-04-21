package br.com.cascao.realmofcard.negocio.strategy.carrinho;

import br.com.cascao.realmofcard.domain.Carrinho;
import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Item;
import br.com.cascao.realmofcard.domain.Status;
import br.com.cascao.realmofcard.negocio.strategy.IStrategy;
import br.com.cascao.realmofcard.repository.CarrinhoRepository;
import br.com.cascao.realmofcard.repository.ItemRepository;
import br.com.cascao.realmofcard.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RetornaItemDisponivel implements IStrategy {

    @Autowired
    ItemRepository itemRepository;

    /*
     *  Método resposável por retirar a quantidade de itens que estava ocupada no carrinho e disponibilizala-la ser
     *  comercializada novamente.
     */

    @Override
    public String processar(EntidadeDominio entidade) {

        if(entidade instanceof Item){

            Item item = (Item) entidade;

            Item itemConsultado = itemRepository.findById(item.getId()).get();

            if(Util.isNotNull(itemConsultado)) {

                Integer quantidadeDisponivelAtualmente = itemConsultado.getCarta().getQuantidadeDisponivel();

                itemConsultado.getCarta().setQuantidadeDisponivel( quantidadeDisponivelAtualmente + itemConsultado.getQuantidade());

                if(itemConsultado.getCarta().getStatus().getStatus().trim().equals("Inativo"))
                    itemConsultado.getCarta().setStatus(Status.builder().id(1).build());

                item.setCarta(itemConsultado.getCarta());

            }

        }

        return null;
    }
}
