package br.com.cascao.realmofcard.persistence;

import br.com.cascao.realmofcard.domain.Carrinho;
import br.com.cascao.realmofcard.domain.Item;
import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.repository.CarrinhoRepository;
import br.com.cascao.realmofcard.repository.CategoriaCartaRepository;
import br.com.cascao.realmofcard.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarrinhoPersistence implements IPersistence {

    @Autowired
    CarrinhoRepository carrinhoRepository;

    @Autowired
    ItemRepository itemRepository;

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {
        if(entidade instanceof Carrinho){
            Carrinho carrinho = (Carrinho) entidade;
            carrinhoRepository.save(carrinho);
            carrinho.getItens().replaceAll(item -> itemRepository.save(item));
            return null;
        }
        return null;
    }

    @Override
    public void alterar(EntidadeDominio entidade) {
        if(entidade instanceof Carrinho) salvar(entidade);
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
