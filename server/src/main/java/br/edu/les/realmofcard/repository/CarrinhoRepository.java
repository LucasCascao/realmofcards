package br.edu.les.realmofcard.repository;

import org.springframework.data.repository.CrudRepository;

import br.edu.les.realmofcard.domain.Carrinho;
import br.edu.les.realmofcard.domain.Item;

import java.util.List;

public interface CarrinhoRepository extends CrudRepository<Carrinho, Integer> {
//    List<Carrinho> findByPessoa_Id(Integer id);

    Carrinho findByPessoa_Id(Integer id);

    Carrinho findByItemListContaining(Item item);
}
