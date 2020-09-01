package br.edu.les.realmofcard.repository;

import org.springframework.data.repository.CrudRepository;

import br.edu.les.realmofcard.domain.ItemTransacao;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemTransicaoRepository extends CrudRepository<ItemTransacao, Integer> { }
