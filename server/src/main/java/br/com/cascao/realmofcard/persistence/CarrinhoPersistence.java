package br.com.cascao.realmofcard.persistence;

import br.com.cascao.realmofcard.domain.Carrinho;
import br.com.cascao.realmofcard.domain.Item;
import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.repository.CarrinhoRepository;
import br.com.cascao.realmofcard.repository.CartaRepository;
import br.com.cascao.realmofcard.repository.CategoriaCartaRepository;
import br.com.cascao.realmofcard.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarrinhoPersistence implements IPersistence {

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CartaRepository cartaRepository;

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {
        if(entidade instanceof Carrinho){
            Carrinho carrinho = (Carrinho) entidade;
            for(Item item : carrinho.getItemList()) {
                cartaRepository.save(item.getCarta());
                itemRepository.save(item);
            };
            carrinho = carrinhoRepository.save(carrinho);
            return null;
        }
        return null;
    }

    @Override
    public void alterar(EntidadeDominio entidade) {
        if(entidade instanceof Carrinho) this.salvar(entidade);
    }

    @Override
    public void excluir(EntidadeDominio entidade) {}

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        List<EntidadeDominio> carrinhos = new ArrayList<>();
        if(entidade instanceof Carrinho){
            Carrinho carrinho = (Carrinho) entidade;
            carrinhos.add(carrinhoRepository.findByPessoa_Id(carrinho.getPessoa().getId()));
            return carrinhos;
        } else return null;
    }
}
