package br.edu.les.realmofcard.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.les.realmofcard.domain.Carrinho;
import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.Item;
import br.edu.les.realmofcard.repository.CarrinhoRepository;
import br.edu.les.realmofcard.repository.CartaRepository;

import java.util.List;

@Service
public class ItemDAO implements IDAO {

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
