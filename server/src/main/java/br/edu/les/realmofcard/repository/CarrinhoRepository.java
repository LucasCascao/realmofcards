package br.edu.les.realmofcard.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.edu.les.realmofcard.domain.Carrinho;
import br.edu.les.realmofcard.domain.Item;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarrinhoRepository extends CrudRepository<Carrinho, Integer> {
//    List<Carrinho> findByPessoa_Id(Integer id);

    Carrinho findByPessoa_Id(Integer id);

    Carrinho findByItemListContaining(Item item);

    @Query("select c from Carrinho c join fetch c.itemList i where i.carta.id = :id")
    List<Carrinho> findByItemListContainingCartaId(@Param("id") Integer id);

}
