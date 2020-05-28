package br.edu.les.realmofcard.repository;

import org.springframework.data.repository.CrudRepository;

import br.edu.les.realmofcard.domain.Bandeira;
import br.edu.les.realmofcard.domain.Rastreio;

public interface RastreioRepository extends CrudRepository<Rastreio, Integer> {
}
