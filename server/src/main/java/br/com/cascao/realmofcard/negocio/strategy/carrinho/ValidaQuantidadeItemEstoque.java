package br.com.cascao.realmofcard.negocio.strategy.carrinho;

import br.com.cascao.realmofcard.domain.Carrinho;
import br.com.cascao.realmofcard.domain.Carta;
import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.negocio.strategy.IStrategy;
import br.com.cascao.realmofcard.repository.CarrinhoRepository;
import br.com.cascao.realmofcard.repository.CartaRepository;
import br.com.cascao.realmofcard.util.validador.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaQuantidadeItemEstoque implements IStrategy {

    @Autowired
    CarrinhoRepository carrinhoRepository;

    @Autowired
    CartaRepository cartaRepository;

    @Override
    public String processar(EntidadeDominio entidade) {

        StringBuilder msg = new StringBuilder();

        if(entidade instanceof Carrinho){

            Carrinho carrinho = (Carrinho) entidade;

            if(Util.isNotNull(carrinho.getItens())){

                carrinho.getItens().forEach( item -> {

                    Carta carta = cartaRepository.findById(item.getCarta().getId()).get();

                    if(item.getQuantidade() > carta.getQuantidade())
                        msg.append("Quantidade pedido não está disponível no estoque.");

                });
            }
        }
        return msg.toString();
    }
}
