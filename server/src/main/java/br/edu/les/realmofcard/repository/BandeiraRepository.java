package br.edu.les.realmofcard.repository;

import org.springframework.data.repository.CrudRepository;

import br.edu.les.realmofcard.domain.Bandeira;
import org.springframework.stereotype.Repository;

@Repository
public interface BandeiraRepository extends CrudRepository<Bandeira, Integer> { }
