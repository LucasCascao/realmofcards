package br.edu.les.realmofcard.repository;

import org.springframework.data.repository.CrudRepository;

import br.edu.les.realmofcard.domain.Bandeira;
import br.edu.les.realmofcard.domain.Carrinho;

public interface BandeiraRepository extends CrudRepository<Bandeira, Integer> { }
