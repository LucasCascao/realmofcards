package br.com.cascao.realmofcard.persistence;

import br.com.cascao.realmofcard.domain.Carrinho;
import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Item;
import br.com.cascao.realmofcard.repository.CarrinhoRepository;
import br.com.cascao.realmofcard.repository.CartaRepository;
import br.com.cascao.realmofcard.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemPersistence implements IPersistence {

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private CartaRepository cartaRepository;

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {
        return null;
    }

    @Override
    public void alterar(EntidadeDominio entidade) {

    }

    @Override
    public void excluir(EntidadeDominio entidade) {
        if(entidade instanceof Item) {
            Item item = (Item) entidade;

            cartaRepository.save(item.getCarta());
            Carrinho carrinho = carrinhoRepository.findByItemListContaining(item);
            carrinho.getItemList().removeIf( itemResultado -> itemResultado.getId() == item.getId());
            carrinhoRepository.save(carrinho);
        }
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        return null;
    }
}
