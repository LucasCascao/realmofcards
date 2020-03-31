package br.com.cascao.realmofcard.negocio.strategy.carrinho;

import br.com.cascao.realmofcard.domain.Carrinho;
import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.negocio.strategy.IStrategy;
import br.com.cascao.realmofcard.repository.CarrinhoRepository;
import br.com.cascao.realmofcard.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

            if(carrinho.getPessoa().getId() != null){
                List<Carrinho> carrinhos  = carrinhoRepository.findByPessoa_Id(carrinho.getPessoa().getId());
                carrinhos.forEach(carrinhoAchado -> {
                    carrinhoAchado.getItens().forEach( item -> {
                        if(item.getCarta().getStatus().getId() == 2){
                            itemRepository.deleteById(item.getId());
                            msg.append("Produto " + item.getCarta().getNome() + " foi retirado do carrinho devido o mesmo estar esgotado.");
                        }
                    });
                });
            }

        }

        return msg.toString();
    }
}
