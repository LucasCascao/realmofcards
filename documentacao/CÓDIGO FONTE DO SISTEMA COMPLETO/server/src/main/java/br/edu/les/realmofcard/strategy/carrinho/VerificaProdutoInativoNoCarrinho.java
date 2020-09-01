package br.edu.les.realmofcard.strategy.carrinho;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.les.realmofcard.domain.Carrinho;
import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.strategy.IStrategy;
import br.edu.les.realmofcard.repository.CarrinhoRepository;
import br.edu.les.realmofcard.repository.ItemRepository;
import br.edu.les.realmofcard.util.Util;

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
                    carrinhoEncontrado.getItemList().removeIf( item -> item.getCarta().getQuantidadeEstoque() <= 0 );
                    carrinhoRepository.save(carrinhoEncontrado);
                }
            }
        }
        return msg.toString();
    }
}
