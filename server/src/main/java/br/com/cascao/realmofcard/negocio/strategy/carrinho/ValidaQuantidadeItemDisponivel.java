package br.com.cascao.realmofcard.negocio.strategy.carrinho;

import br.com.cascao.realmofcard.domain.Carrinho;
import br.com.cascao.realmofcard.domain.Carta;
import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Item;
import br.com.cascao.realmofcard.negocio.strategy.IStrategy;
import br.com.cascao.realmofcard.repository.CarrinhoRepository;
import br.com.cascao.realmofcard.repository.CartaRepository;
import br.com.cascao.realmofcard.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidaQuantidadeItemDisponivel implements IStrategy {

    @Autowired
    CarrinhoRepository carrinhoRepository;

    @Autowired
    CartaRepository cartaRepository;

    @Override
    public String processar(EntidadeDominio entidade) {

        StringBuilder msg = new StringBuilder();

        if(entidade instanceof Carrinho || entidade instanceof Item){

            if(entidade instanceof Carrinho){
                List<Item> itemList = ((Carrinho) entidade).getItemList();

                if(Util.isNotNull(itemList)){

                    itemList.forEach( item -> {

                        Carta carta = cartaRepository.findById(item.getCarta().getId()).get();

                        if(item.getQuantidade() > carta.getQuantidadeEstoque())
                            msg.append("Quantidade pedido não está disponível no estoque.");

                        if(item.getQuantidade() < 1)
                            msg.append("Quantidade pedido não é válida para incluir no carrinho.");

                    });
                }
            }

            if(entidade instanceof Item) {

                Item item = (Item) entidade;

                Carta carta = cartaRepository.findById(item.getCarta().getId()).get();

                if(item.getQuantidade() > carta.getQuantidadeEstoque())
                    msg.append("Quantidade pedido não está disponível no estoque.");
            }

        }
        return msg.toString();
    }
}
