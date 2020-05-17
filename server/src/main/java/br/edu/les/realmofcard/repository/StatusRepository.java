package br.edu.les.realmofcard.repository;

import org.springframework.data.repository.CrudRepository;

import br.edu.les.realmofcard.domain.CartaoCredito;
import br.edu.les.realmofcard.domain.Status;

import java.util.List;

public interface StatusRepository extends CrudRepository<Status, Integer> {

}
