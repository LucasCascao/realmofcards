package br.edu.les.realmofcard.dao;

import br.edu.les.realmofcard.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.les.realmofcard.domain.Carrinho;
import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.Item;
import br.edu.les.realmofcard.repository.CarrinhoRepository;
import br.edu.les.realmofcard.repository.CartaRepository;
import br.edu.les.realmofcard.repository.ItemRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarrinhoDAO implements IDAO {

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
        Carrinho carrinho = (Carrinho) entidade;
        if(Util.isNotNull(carrinho.getPessoa()) && Util.isNotNull(carrinho.getPessoa().getId())){
            carrinhos.add(carrinhoRepository.findByPessoa_Id(carrinho.getPessoa().getId()));
            return carrinhos;
        }
        carrinhoRepository.findAll().forEach(carrinhoResultado -> carrinhos.add(carrinhoResultado));
        return carrinhos;
    }
}
