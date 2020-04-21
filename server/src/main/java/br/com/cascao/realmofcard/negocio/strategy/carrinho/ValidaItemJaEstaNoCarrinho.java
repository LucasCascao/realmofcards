package br.com.cascao.realmofcard.negocio.strategy.carrinho;

import br.com.cascao.realmofcard.domain.Carrinho;
import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.negocio.strategy.IStrategy;
import br.com.cascao.realmofcard.repository.CarrinhoRepository;
import br.com.cascao.realmofcard.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
