package br.com.cascao.realmofcard.negocio.strategy.carrinho;

import br.com.cascao.realmofcard.domain.Carrinho;
import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.negocio.strategy.IStrategy;
import br.com.cascao.realmofcard.repository.CarrinhoRepository;
import br.com.cascao.realmofcard.util.validador.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PegaCarrinhoSeExistir implements IStrategy {

    @Autowired
    CarrinhoRepository carrinhoRepository;

    @Override
    public String processar(EntidadeDominio entidade) {

        StringBuilder msg = new StringBuilder();

        if(entidade instanceof Carrinho){

            Carrinho carrinho = (Carrinho) entidade;

            if(carrinho.getPessoa() != null
                    && carrinho.getPessoa().getId() != null){

                Carrinho carrinhoResultado = carrinhoRepository.findByPessoa_Id(carrinho.getPessoa().getId());

                if(Util.isNotNull(carrinhoResultado)){

                    carrinho.setId(carrinhoResultado.getId());
                    carrinho.getItens().addAll(carrinhoResultado.getItens());
                }

            }

        }

        return msg.toString();
    }
}
