package br.edu.les.realmofcard.repository;

import org.springframework.data.repository.CrudRepository;

import br.edu.les.realmofcard.domain.Carrinho;
import br.edu.les.realmofcard.domain.Item;

public interface ItemRepository extends CrudRepository<Item, Integer> {
}
