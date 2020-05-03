package br.com.cascao.realmofcard.negocio.strategy.carrinho;

import br.com.cascao.realmofcard.domain.Carrinho;
import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.negocio.strategy.IStrategy;
import br.com.cascao.realmofcard.repository.CartaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
                         msg.append("Produto " + item.getCarta().getNome() + " est� inativo no momento.");
                    }
                });
            }else{
                msg.append("Carrinho est� vazio");
            }
        }

        return msg.toString();
    }
}
