package br.com.cascao.realmofcard.negocio.strategy.pedido;

import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Item;
import br.com.cascao.realmofcard.domain.Pedido;
import br.com.cascao.realmofcard.negocio.strategy.IStrategy;
import br.com.cascao.realmofcard.repository.CartaRepository;
import br.com.cascao.realmofcard.repository.ItemRepository;
import br.com.cascao.realmofcard.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
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
