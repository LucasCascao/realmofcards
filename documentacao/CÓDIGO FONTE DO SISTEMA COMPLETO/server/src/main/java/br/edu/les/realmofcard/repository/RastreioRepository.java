package br.edu.les.realmofcard.repository;

import org.springframework.data.repository.CrudRepository;

import br.edu.les.realmofcard.domain.Bandeira;
import br.edu.les.realmofcard.domain.Rastreio;
import org.springframework.stereotype.Repository;

@Repository
public interface RastreioRepository extends CrudRepository<Rastreio, Integer> {
}
