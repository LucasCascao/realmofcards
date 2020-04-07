package br.com.cascao.realmofcard.negocio.strategy.carrinho;

import br.com.cascao.realmofcard.domain.Carrinho;
import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.negocio.strategy.IStrategy;
import br.com.cascao.realmofcard.repository.CarrinhoRepository;
import br.com.cascao.realmofcard.repository.ItemRepository;
import br.com.cascao.realmofcard.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VerificaProdutoInativoNoCarrinho implements IStrategy {

    @Autowired
    CarrinhoRepository carrinhoRepository;

    @Autowired
    ItemRepository itemRepository;

    @Override
    public String processar(EntidadeDominio entidade) {

        StringBuilder msg = new StringBuilder();

        if(entidade instanceof Carrinho){

            Carrinho carrinho = (Carrinho) entidade;

            if(Util.isNotNull(carrinho.getPessoa())
                && Util.isNotNull(carrinho.getPessoa().getId())){

                Carrinho carrinhoEncontrado  = carrinhoRepository.findByPessoa_Id(carrinho.getPessoa().getId());

                if(Util.isNotNull(carrinhoEncontrado)){
                    carrinhoEncontrado.getItens().removeIf( item -> item.getCarta().getStatus().getId() == 2);
                    carrinhoRepository.save(carrinhoEncontrado);
                }
            }
        }
        return msg.toString();
    }
}
