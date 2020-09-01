package br.edu.les.realmofcard.strategy.carta;

import br.edu.les.realmofcard.dao.CarrinhoDAO;
import br.edu.les.realmofcard.dao.CartaDAO;
import br.edu.les.realmofcard.dao.ItemDAO;
import br.edu.les.realmofcard.domain.Carrinho;
import br.edu.les.realmofcard.domain.Carta;
import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.Item;
import br.edu.les.realmofcard.repository.CarrinhoRepository;
import br.edu.les.realmofcard.repository.ItemRepository;
import br.edu.les.realmofcard.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Component
public class RetiraCartaNaoDisponivelDoCarrinho implements IStrategy {

    @Autowired
    private CartaDAO cartaDAO;

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemDAO itemDAO;

    @Override
    public String processar(EntidadeDominio entidade) {

        if(entidade instanceof Carta){
            Carta carta = (Carta) entidade;
            if(carta.getQuantidadeDisponivel() <= 0 || carta.getStatus().getId() == 2){
                Integer quantidadeRetornada = 0;
                List<Carrinho> carrinhos = carrinhoRepository.findByItemListContainingCartaId(carta.getId());
                for (Carrinho carrinho : carrinhos) {
                    List<Item> itemsRemovidos = new ArrayList<>();
                    for (Item item : carrinho.getItemList()) {
                        if(item.getCarta().getId() == carta.getId() && carta.getQuantidadeDisponivel() < item.getQuantidade()){
                            quantidadeRetornada += item.getQuantidade();
                            itemsRemovidos.add(item);
                        }
                    }
                    if(itemsRemovidos.size() > 0){
                        carrinho.getItemList().removeAll(itemsRemovidos);
                        carrinhoRepository.save(carrinho);
                        itemRepository.deleteAll(itemsRemovidos);
                    }
                }
                if(quantidadeRetornada > 0 && quantidadeRetornada <= carta.getQuantidadeEstoque()){
                    carta.setQuantidadeDisponivel(quantidadeRetornada);
                } else {
                    carta.setQuantidadeDisponivel(0);
                }
            }
        }

        return null;
    }
}
