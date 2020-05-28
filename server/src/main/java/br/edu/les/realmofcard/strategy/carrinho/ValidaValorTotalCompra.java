package br.edu.les.realmofcard.strategy.carrinho;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.les.realmofcard.domain.Carrinho;
import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.strategy.IStrategy;
import br.edu.les.realmofcard.repository.CartaRepository;

@Component
public class ValidaValorTotalCompra implements IStrategy {

    @Autowired
    CartaRepository cartaRepository;

    @Override
    public String processar(EntidadeDominio entidade) {

        StringBuilder msg = new StringBuilder();

        if(entidade instanceof Carrinho){

            Carrinho carrinho = (Carrinho) entidade;

            if(!carrinho.getItemList().isEmpty()){
                carrinho.getItemList().forEach( item -> {
                    if (cartaRepository.findById(item.getCarta().getId()).get()
                            .getStatus().getId() == 2) {
                         msg.append("Produto " + item.getCarta().getNome() + " está inativo no momento.");
                    }
                });
            }else{
                msg.append("Carrinho está vazio");
            }
        }

        return msg.toString();
    }
}
