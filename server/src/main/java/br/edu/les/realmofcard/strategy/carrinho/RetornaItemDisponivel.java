package br.edu.les.realmofcard.strategy.carrinho;

import br.edu.les.realmofcard.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.Item;
import br.edu.les.realmofcard.domain.Status;
import br.edu.les.realmofcard.repository.ItemRepository;
import br.edu.les.realmofcard.util.Util;

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
