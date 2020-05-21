package br.edu.les.realmofcard.strategy.carrinho;

import br.edu.les.realmofcard.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.les.realmofcard.domain.Carrinho;
import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.repository.CarrinhoRepository;
import br.edu.les.realmofcard.util.Util;

@Component
public class ValidaItemJaEstaNoCarrinho implements IStrategy {

    @Autowired
    CarrinhoRepository carrinhoRepository;

    @Override
    public String processar(EntidadeDominio entidade) {

        StringBuilder msg = new StringBuilder();

        if(entidade instanceof Carrinho){

            Carrinho carrinho = (Carrinho) entidade;

            Carrinho carrinhoResultado = carrinhoRepository.findByPessoa_Id(carrinho.getPessoa().getId());

            if(Util.isNotNull(carrinhoResultado)) {
                carrinhoResultado.getItemList().forEach( item -> {
                    if(Util.isEquals(item.getCarta().getId(),
                            carrinho.getItemList().get(0).getCarta().getId()))
                        msg.append("Carta já está no carrinho.");
                });
            }
        }
        return msg.toString();
    }
}
