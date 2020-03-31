package br.com.cascao.realmofcard.negocio.strategy.carrinho;

import br.com.cascao.realmofcard.domain.Carrinho;
import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.negocio.strategy.IStrategy;
import br.com.cascao.realmofcard.repository.CarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VerificaCarrinhoAtivo implements IStrategy {

    @Autowired
    CarrinhoRepository carrinhoRepository;

    @Override
    public String processar(EntidadeDominio entidade) {

        StringBuilder msg = new StringBuilder();

        if(entidade instanceof Carrinho){
            Carrinho carrinho = (Carrinho) entidade;
            if(carrinho.getPessoa() != null
            && carrinhoRepository.findByPessoa_Id(carrinho.getPessoa().getId()) != null){
                carrinho = carrinhoRepository.findByPessoa_Id(carrinho.getPessoa().getId()).get(0);
            }
        }

        return msg.toString();
    }
}
