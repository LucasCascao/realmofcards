package br.edu.les.realmofcard.strategy.pedido;

import br.edu.les.realmofcard.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.Item;
import br.edu.les.realmofcard.domain.Pedido;
import br.edu.les.realmofcard.repository.CartaRepository;
import br.edu.les.realmofcard.util.Util;

import java.util.Set;

@Component
public class AtualizaItensPedidos implements IStrategy {

    @Autowired
    private CartaRepository cartaRepository;

    @Override
    public String processar(EntidadeDominio entidade) {

        if(entidade instanceof Pedido){

            Pedido pedido = (Pedido) entidade;

            if(Util.isNotNull(pedido.getItemList())){
                Set<Item> itemList = pedido.getItemList();
                for (Item item : itemList) {
                    if(Util.isNotNull(item.getId()))
                    item.setCarta(cartaRepository.findById(item.getCarta().getId()).get());
                }
            }

        }

        return null;
    }
}
